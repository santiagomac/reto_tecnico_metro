package com.co.macla.retotecnico.application;

import com.co.macla.retotecnico.domain.usecase.TransactionInsertUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public TransactionInsertUseCase transactionInsertUseCase() {
        return new TransactionInsertUseCase();
    }
}
