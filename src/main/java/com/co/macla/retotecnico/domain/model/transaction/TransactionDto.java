package com.co.macla.retotecnico.domain.model.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Double amount;
}
