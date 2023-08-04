package com.demo.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb")
@Getter
@Setter
public class CustomMongoProperties {
    private MongoProperties test;
    private MongoProperties reactive;

}