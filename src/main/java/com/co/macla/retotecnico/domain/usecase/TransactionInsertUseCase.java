package com.co.macla.retotecnico.domain.usecase;

import com.co.macla.retotecnico.domain.model.transaction.TransactionDto;
import com.co.macla.retotecnico.domain.model.transaction.gateways.TransactionGateway;
import com.co.macla.retotecnico.infrastructure.driven_adapter.kafka.TransactionProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
public class TransactionInsertUseCase {

    private final TransactionGateway transactionGateway;
    private final TransactionProducer transactionProducer;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;


    public TransactionDto createTransaction(TransactionDto transactionDto) {
        try {
            LocalDateTime date = LocalDateTime.parse(transactionDto.getTimestamp().toString(), FORMATTER);
            transactionDto.setTimestamp(date);
            TransactionDto transactionSaved = this.transactionGateway.insert(transactionDto);
            this.transactionProducer.publish(transactionSaved);
            this.transactionGateway.aggregate();
            return transactionSaved;
        }catch (Exception e){
            throw e;
        }
    }
}
