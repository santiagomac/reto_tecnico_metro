package com.co.macla.retotecnico.domain.model.transaction.gateways;

import com.co.macla.retotecnico.domain.model.transaction.TransactionDto;

public interface TransactionGateway {

    TransactionDto insert(TransactionDto transactionDto);
    void aggregate();
}
