package org.koko;

import org.apache.spark.SparkConf;

import java.util.HashMap;

/**
 * PipelineConfig
 */
public class PipelineConfig {

    private final String cassandraHost;
    private final String sparkMasterHost;
    private final String kafkaBrokers;
    private final SparkConf sparkConf;

    public PipelineConfig(String cassandraHost, String sparkMasterHost, String kafkaBrokers) {
        this.cassandraHost = cassandraHost;
        this.sparkMasterHost = sparkMasterHost;
        this.kafkaBrokers = kafkaBrokers;
        this.sparkConf = new SparkConf();
        this.sparkConf.setMaster(sparkMasterHost);
        this.sparkConf.set("spark.cassandra.connection.host", cassandraHost);
    }

    public HashMap<String, String> kafkaParams() {
        HashMap<String, String> kafkaParams = new HashMap<>();
        kafkaParams.put("metadata.broker.list", kafkaBrokers);
        return kafkaParams;
    }

    public String getCassandraHost() {
        return cassandraHost;
    }

    public String getSparkMasterHost() {
        return sparkMasterHost;
    }

    public String getKafkaBrokers() {
        return kafkaBrokers;
    }

    public SparkConf getSparkConf() {
        return sparkConf;
    }

}
