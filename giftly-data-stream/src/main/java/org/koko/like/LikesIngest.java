package org.koko.like;

import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;
import com.fasterxml.jackson.databind.ObjectMapper;

import kafka.serializer.StringDecoder;

import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import org.koko.PipelineConfig;

import scala.Tuple2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;
import static com.datastax.spark.connector.japi.CassandraJavaUtil.mapToRow;

public class LikesIngest implements Serializable {

    private final transient PipelineConfig conf;
    private final String inputTopic;

    public LikesIngest(PipelineConfig conf, String inputTopic) {
        this.conf = conf;
        this.inputTopic = inputTopic;
    }

    public void execute() {

        ObjectMapper mapper = new ObjectMapper(/*new SmileFactory()*/);

        CassandraConnector connector = CassandraConnector.apply(conf.getSparkConf());

        JavaStreamingContext javaStreamingContext = new JavaStreamingContext(conf.getSparkConf(), Durations.seconds(5));

        JavaPairInputDStream<String, String> messages = KafkaUtils.createDirectStream(
                javaStreamingContext, String.class, String.class, StringDecoder.class, StringDecoder.class,
                conf.kafkaParams(), new HashSet<>(Arrays.asList(inputTopic.split(","))));

        JavaDStream<Like> likes = messages.map(Tuple2::_2).map(e -> mapper.readValue(e, Like.class));

        likes.foreach(rdd -> {

            javaFunctions(rdd).writerBuilder("giftly", "gift_likes_raw", mapToRow(Like.class)).saveToCassandra();

            rdd.foreach(like -> {

                try (Session session = connector.openSession()) {

                    boolean isLiked = Integer.valueOf(like.getLiked()) == 1;

                    String updateTop = "UPDATE giftly.gift_likes_total SET total = total " + (isLiked ? "+" : "-") + " 1 WHERE gift_id = ?";
                    session.execute(session.prepare(updateTop).bind(like.getGiftId()));

                    String updateCurrent = "UPDATE giftly.gift_likes_current SET liked = ? WHERE gift_id = ? and user_id = ?";
                    session.execute(session.prepare(updateCurrent).bind(Integer.valueOf(like.getLiked()), like.getGiftId(), like.getUserId()));
                }
            });
            return null;
        });

        javaStreamingContext.start();
        javaStreamingContext.awaitTermination();
    }

    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println("run with: org.koko.likes.LikesIngest cassandraHost sparkMasterHost kafkaBrokers");
            System.exit(1);
        }

        PipelineConfig taskConfig = new PipelineConfig(args[0], args[1], args[2]);
        taskConfig.getSparkConf().setAppName("likes ingest");
        LikesIngest likesIngest = new LikesIngest(taskConfig, "likes");
        likesIngest.execute();
    }

}
