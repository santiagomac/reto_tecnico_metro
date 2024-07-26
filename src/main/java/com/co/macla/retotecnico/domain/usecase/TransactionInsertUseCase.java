package com.co.macla.retotecnico.domain.usecase;

import com.co.macla.retotecnico.domain.model.transaction.gateways.TransactionGateway;
import com.co.macla.retotecnico.infrastructure.driven_adapter.mongo.MongoDBRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransactionInsertUseCase {

    private final TransactionGateway transactionGateway;

    public String createTransaction() {
        return "The use case works!";
    }
}
