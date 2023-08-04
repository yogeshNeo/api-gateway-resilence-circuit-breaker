package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

/**
 * Mongo template bean configuration file for query data collection
 */
@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.demo.repository", reactiveMongoTemplateRef = "reactiveMongoTemplate")
public class ReactiveMongoConfig extends AbstractReactiveMongoConfiguration {

    @Autowired
    private CustomMongoProperties customMongoProperties;

    @Primary
    @Bean("mongoClient")
    public MongoClient reactiveMongoClientReactive() {
        MongoClientSettings mongoClientSettings = createMongoClientSettings(customMongoProperties.getReactive());

        return MongoClients.create(mongoClientSettings);
    }

    @Primary
    @Bean("reactiveMongoTemplate")
    public ReactiveMongoTemplate reactiveMongoTemplateForReactiveDB() {

        MongoClient mongoClient = reactiveMongoClientReactive();
        String db = customMongoProperties.getReactive().getDatabase();

        return new ReactiveMongoTemplate(mongoClient, db);
    }

    private MongoClientSettings createMongoClientSettings(MongoProperties mongoProperties) {

        ConnectionString ConnectionString = new ConnectionString(mongoProperties.getUri());

        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .readConcern(ReadConcern.DEFAULT)
                .writeConcern(WriteConcern.MAJORITY)
                .readPreference(ReadPreference.primary())
                .applyConnectionString(ConnectionString).build();

        return mongoClientSettings;
    }

    @Override
    protected String getDatabaseName() {
        return customMongoProperties.getReactive().getDatabase();
    }

}