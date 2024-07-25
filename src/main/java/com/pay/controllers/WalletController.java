package com.pay.controllers;

import com.pay.dto.model.request.TransactionRequest;
import com.pay.dto.model.request.WalletRequest;
import com.pay.dto.model.response.ApiResponse;
import com.pay.dto.model.response.CommandResponse;
import com.pay.dto.model.response.WalletResponse;
import com.pay.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

import static com.pay.commons.Constants.*;

@RestController
@RequestMapping(WALLET_API)
public class WalletController {

    private final Clock clock;

    private final WalletService walletService;

    @Autowired
    public WalletController(Clock clock, WalletService walletService) {
        this.clock = clock;
        this.walletService = walletService;
    }

    @GetMapping(ID)
    public ResponseEntity<ApiResponse<WalletResponse>> findById(@PathVariable Long id) {
        final WalletResponse response = walletService.findById(id);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));
    }

    @GetMapping(BANK_ACCOUNT_NO)
    public ResponseEntity<ApiResponse<WalletResponse>> findByBankAccountNumber(@PathVariable String bankAccountNumber) {
        final WalletResponse response = walletService.findByBankAccountNumber(bankAccountNumber);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now().toEpochMilli(), SUCCESS, response));
    }

    @GetMapping(USER_ID)
    public ResponseEntity<ApiResponse<List<WalletResponse>>> findByUserId(@PathVariable Long userId) {
        final List<WalletResponse> response = walletService.findByUserId(userId);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<WalletResponse>>> findAll(Pageable pageable) {
        final Page<WalletResponse> response = walletService.findAll(pageable);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now().toEpochMilli(), SUCCESS, response));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CommandResponse>> create(@RequestBody WalletRequest request) {
        final CommandResponse response = walletService.createWallet(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(Instant.now().toEpochMilli(), SUCCESS, response));
    }

    @PostMapping(TRANSFER_FUNDS)
    public ResponseEntity<ApiResponse<CommandResponse>> transferFunds(@RequestBody TransactionRequest request) {
        final CommandResponse response = walletService.transferFunds(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(Instant.now().toEpochMilli(), SUCCESS, response));
    }

    @PostMapping(ADD_FUNDS)
    public ResponseEntity<ApiResponse<CommandResponse>> addFunds(@RequestBody TransactionRequest request) {
        final CommandResponse response = walletService.addFunds(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(Instant.now().toEpochMilli(), SUCCESS, response));
    }

    @PostMapping(WITHDRAW_FUNDS)
    public ResponseEntity<ApiResponse<CommandResponse>> withdrawFunds(@RequestBody TransactionRequest request) {
        final CommandResponse response = walletService.withdrawFunds(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(Instant.now().toEpochMilli(), SUCCESS, response));
    }

    @PutMapping(ID)
    public ResponseEntity<ApiResponse<CommandResponse>> update(@PathVariable Long id, @RequestBody WalletRequest request) {
        final CommandResponse response = walletService.update(id, request);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));
    }

    @DeleteMapping(ID)
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Long id) {
        walletService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
