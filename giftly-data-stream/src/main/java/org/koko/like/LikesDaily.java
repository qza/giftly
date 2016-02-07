package org.koko.like;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;
import com.datastax.spark.connector.japi.CassandraRow;
import com.datastax.spark.connector.japi.SparkContextJavaFunctions;

import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import org.koko.PipelineConfig;

import scala.Tuple2;

import java.io.Serializable;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;

/**
 * LikesDaily
 */
public class LikesDaily implements Serializable {

    private final transient PipelineConfig conf;

    public LikesDaily(PipelineConfig conf) {
        this.conf = conf;
    }

    public void execute(String date) {

        JavaStreamingContext javaStreamingContext = new JavaStreamingContext(conf.getSparkConf(), Durations.seconds(10));

        CassandraConnector connector = CassandraConnector.apply(conf.getSparkConf());

        SparkContextJavaFunctions javaFunctions = javaFunctions(javaStreamingContext.sparkContext());

        javaFunctions
                .cassandraTable("giftly", "gift_likes_raw")
                .where("like_time >= '" + date + " 00:00:00' AND  like_time <= '" + date + " 23:59:59' and liked=1")
                .mapToPair((PairFunction<CassandraRow, String, Long>) row ->
                        new Tuple2<>(row.getString("gift_id"), Long.valueOf(1))
                )
                .reduceByKey((Function2<Long, Long, Long>) (aLong, aLong2) -> aLong + aLong2)
                .foreach((keyval) -> {
                    try (Session session = connector.openSession()) {
                        String queryString = "UPDATE giftly.gift_likes_daily SET total = ? WHERE gift_id = ? AND date = ?";
                        PreparedStatement prepared = session.prepare(queryString);
                        session.execute(prepared.bind(keyval._2(), keyval._1(), date));
                    }
                });

        javaStreamingContext.start();
        javaStreamingContext.awaitTermination();
    }

    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println("run with: org.koko.likes.LikesDaily 2016-01-01 cassandraHost sparkMasterHost");
            System.exit(1);
        }

        PipelineConfig taskConfig = new PipelineConfig(args[1], args[2], null);
        taskConfig.getSparkConf().setAppName("likes daily");
        LikesDaily likesDaily = new LikesDaily(taskConfig);
        likesDaily.execute(args[0]);
    }

}
