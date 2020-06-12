package com.myretail.myretailApp.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.client.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;

public class MongoConfig {
	
	private static final String MONGO_DB_URL = "localhost";
    private static final String MONGO_DB_NAME = "productdb";
    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongoFactoryBean = new EmbeddedMongoFactoryBean();
        mongoFactoryBean.setBindIp(MONGO_DB_URL);
        MongoClient mongoClient = (MongoClient) mongoFactoryBean.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
        return mongoTemplate;
    }
    
}
