package com.co.macla.retotecnico.infrastructure.entry_points;

import com.co.macla.retotecnico.domain.model.transaction.TransactionDto;
import com.co.macla.retotecnico.domain.usecase.TransactionInsertUseCase;
import com.co.macla.retotecnico.infrastructure.entry_points.dto.ErrorResponse;
import com.co.macla.retotecnico.infrastructure.entry_points.dto.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class Controller {

    private final TransactionInsertUseCase transactionInsertUseCase;
    private final ObjectMapper mapper;

    @PostMapping()
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        try{
            TransactionDto transactionDto = mapper.map(transactionRequest, TransactionDto.class);
            return new ResponseEntity<>(this.transactionInsertUseCase.createTransaction(transactionDto), HttpStatus.CREATED);
        }catch (Exception e){
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .message("Error saving the transaction")
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
