package com.co.macla.retotecnico.infrastructure.driven_adapter.mongo;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransactionEntity {

    private String transactionId;
    private LocalDateTime timestamp;
    private Integer deviceNumber;
    private String userId;
    private String geoPosition;
    private BigDecimal amount;
}
