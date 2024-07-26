package com.co.macla.retotecnico.infrastructure.entry_points.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransactionRequest {

    private String transactionId;
    private LocalDateTime timestamp;
    private Integer deviceNumber;
    private String userId;
    private String geoPosition;
    private BigDecimal amount;
}
