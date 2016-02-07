package org.koko;

import org.koko.gift.Gift;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.mongodb.core.MongoOperations;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;

/**
 * AppRepos
 */
@Configuration
@ConditionalOnExpression("${mongo.enabled:true}")
public class AppRepos {

    private final Logger log = LoggerFactory.getLogger(App.class);

    @Autowired
    private MongoOperations mongoOperations;

    @PostConstruct
    public void initializeData() {

        try {
            if (mongoOperations != null && !mongoOperations.collectionExists(Gift.class)) {

                Collection<Gift> sampleData = new ArrayList<>();
                sampleData.add(new Gift("star wars t-shirt white"));
                sampleData.add(new Gift("star wars t-shirt blue"));
                sampleData.add(new Gift("star wars t-shirt black"));

                mongoOperations.createCollection(Gift.class);
                mongoOperations.insert(sampleData, Gift.class);

                log.info("sample data added [gift count:{}]", mongoOperations.getCollection("gifts").count());
            }
        } catch (DataAccessResourceFailureException ex) {
            log.error("failed connecting to mongo db");
        }
    }

}
