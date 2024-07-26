package com.co.macla.retotecnico.infrastructure.driven_adapter.kafka;

import com.co.macla.retotecnico.domain.model.transaction.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionProducer {

    private final KafkaTemplate<String, TransactionDto> kafkaTemplate;

    public void publish(TransactionDto transactionDto) {
        kafkaTemplate.send("transactions", UUID.randomUUID().toString(), transactionDto);
    }
}
