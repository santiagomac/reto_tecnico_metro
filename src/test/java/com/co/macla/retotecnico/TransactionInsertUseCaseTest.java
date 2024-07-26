package com.co.macla.retotecnico;

import com.co.macla.retotecnico.domain.model.transaction.TransactionDto;
import com.co.macla.retotecnico.domain.model.transaction.gateways.TransactionGateway;
import com.co.macla.retotecnico.domain.usecase.TransactionInsertUseCase;
import com.co.macla.retotecnico.infrastructure.driven_adapter.kafka.TransactionProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionInsertUseCaseTest {

    @Mock
    private TransactionGateway transactionGateway;

    @Mock
    private TransactionProducer transactionProducer;

    @InjectMocks
    private TransactionInsertUseCase transactionInsertUseCase;

    @Test
    void shouldInsertAndReturnTheTransactionInserted() {
        TransactionDto initTransactionDto = this.createInitTransactionDto();
        when(this.transactionGateway.insert(any(TransactionDto.class))).thenReturn(initTransactionDto);
        TransactionDto transactionResult = this.transactionInsertUseCase.createTransaction(initTransactionDto);
        verify(this.transactionProducer, times(1)).publish(any());
        Assertions.assertEquals(initTransactionDto, transactionResult);
    }

    @Test
    void shouldThrowAndException() {
        TransactionDto initTransactionDto = this.createInitTransactionDto();
        when(this.transactionGateway.insert(any(TransactionDto.class))).thenThrow(new RuntimeException("Database is down"));
        assertThrows(RuntimeException.class, () -> {
            this.transactionInsertUseCase.createTransaction(initTransactionDto);
        });

        verify(this.transactionProducer, times(0)).publish(any());
    }


    private TransactionDto createInitTransactionDto() {
        return TransactionDto.builder()
                .transactionId("1231231")
                .userId("123456")
                .deviceNumber(10)
                .geoPosition("40.741 -73.989")
                .amount(12000.0)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
