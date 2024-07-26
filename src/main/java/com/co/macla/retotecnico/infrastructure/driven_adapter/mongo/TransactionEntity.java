package com.co.macla.retotecnico.infrastructure.driven_adapter.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "transactions")
public class TransactionEntity {

    private String transactionId;
    private LocalDateTime timestamp;
    private Integer deviceNumber;
    private String userId;
    private String geoPosition;
    private Double amount;
}
