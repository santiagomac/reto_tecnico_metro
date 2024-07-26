package com.co.macla.retotecnico.infrastructure.driven_adapter.mongo.helper;

import com.co.macla.retotecnico.infrastructure.driven_adapter.mongo.TransactionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract class MongoDBAdapter <R extends MongoRepository<TransactionEntity, String>>{
}
