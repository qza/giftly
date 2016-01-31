package org.koko;

import org.koko.gift.Gift;

import org.koko.gift.GiftInputChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;

import org.springframework.cloud.stream.annotation.EnableBinding;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(GiftInputChannel.class)
public class App {

    static final Logger log = LoggerFactory.getLogger(App.class);

    @Autowired
    MongoOperations operations;

    @Bean
    public AlwaysSampler alwaysSampler() {
        return new AlwaysSampler();
    }

    @PostConstruct
    public void initializeData() {

        if (!operations.collectionExists(Gift.class)) {

            Collection<Gift> sampleData = new ArrayList<>();
            sampleData.add(new Gift("star wars t-shirt white"));
            sampleData.add(new Gift("star wars t-shirt blue"));
            sampleData.add(new Gift("star wars t-shirt black"));

            operations.createCollection(Gift.class);
            operations.insert(sampleData, Gift.class);

            log.info("sample data added [gift count:{}]", operations.getCollection("gifts").count());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
