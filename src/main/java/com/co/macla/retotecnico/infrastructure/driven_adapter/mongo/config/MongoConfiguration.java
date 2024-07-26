package com.co.macla.retotecnico.infrastructure.driven_adapter.mongo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoConnectionDetails;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.PropertiesMongoConnectionDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration {

    @Bean
    public MongoDBSecret dbSecret(@Value("${spring.data.mongo.uri}") String uri) {
        return MongoDBSecret.builder()
                .uri(uri)
                .build();
    }


    @Bean
    public MongoConnectionDetails mongoProperties(MongoDBSecret secret) {
        MongoProperties properties = new MongoProperties();
        properties.setUri(secret.getUri());
        return new PropertiesMongoConnectionDetails(properties);

    }
}