package com.co.macla.retotecnico.infrastructure.driven_adapter.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

public interface MongoDBRepository extends MongoRepository<TransactionEntity, String>, QueryByExampleExecutor<TransactionEntity> {
}
