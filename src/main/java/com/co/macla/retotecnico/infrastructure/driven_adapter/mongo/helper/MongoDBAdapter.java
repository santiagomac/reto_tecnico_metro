package com.co.macla.retotecnico.infrastructure.driven_adapter.mongo.helper;

import com.co.macla.retotecnico.infrastructure.driven_adapter.mongo.TransactionEntity;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract class MongoDBAdapter<R extends MongoRepository<TransactionEntity, String>> {

    protected R repository;
    protected ObjectMapper mapper;
    protected MongoTemplate mongoTemplate;

    protected MongoDBAdapter(R repository, ObjectMapper mapper, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mapper = mapper;
        this.mongoTemplate = mongoTemplate;
    }
}
