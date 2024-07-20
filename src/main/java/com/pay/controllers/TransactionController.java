package com.pay.controllers;

import com.pay.commons.Constants;
import com.pay.dto.model.response.ApiResponse;
import com.pay.dto.model.response.TransactionResponse;
import com.pay.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.Instant;

import static com.pay.commons.Constants.*;

@RestController
@RequestMapping(TRANSACTION_API)
public class TransactionController {

    private final Clock clock;

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(Clock clock, TransactionService transactionService) {
        this.clock = clock;
        this.transactionService = transactionService;
    }

    @GetMapping(ID)
    public ResponseEntity<ApiResponse<TransactionResponse>> findById(@PathVariable Long id) {
        final TransactionResponse response = transactionService.findById(id);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now().toEpochMilli(), SUCCESS, response));
    }

    @GetMapping(REFERENCE_NUMBER)
    public ResponseEntity<ApiResponse<TransactionResponse>> findByReferenceNumber(@PathVariable String referenceNumber) {
        final TransactionResponse response = transactionService.findByReferenceNumber(referenceNumber);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now().toEpochMilli(), SUCCESS, response));
    }

    @GetMapping(USER_ID)
    public ResponseEntity<ApiResponse<Page<TransactionResponse>>> findAllByUserId(@PathVariable Long userId) {
        final Page<TransactionResponse> response = (transactionService.findAllByUserId(userId));
        return ResponseEntity.ok(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<TransactionResponse>>> findAll(Pageable pageable) {
        final Page<TransactionResponse> response = transactionService.findAll(pageable);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));
    }
}
