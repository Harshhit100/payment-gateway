package com.pay.dto.model.request;

import com.pay.model.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TransactionRequest {

    private Long id;

    private BigDecimal amount;

    private String description;

    private String fromWalletBankAccountNumber;

    private String toWalletBankAccountNumber;

    private Instant createdAt;

    private String referenceNumber;

    private Status status;

    private Long typeId;
}