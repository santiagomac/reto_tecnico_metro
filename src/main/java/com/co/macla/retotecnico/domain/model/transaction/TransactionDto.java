package com.co.macla.retotecnico.domain.model.transaction;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransactionDto {

    private String transactionId;
    private LocalDateTime timestamp;
    private Integer deviceNumber;
    private String userId;
    private String geoPosition;
    private BigDecimal amount;
}
