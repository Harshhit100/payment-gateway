package com.pay.dto.model.request;

import com.pay.model.Status;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TransactionRequest {

    private Long id;

    @NotNull
    private BigDecimal amount;

    private String description;

    private Instant createdAt;

    private String referenceNumber;

    private Status status;

    private String fromWallet;

    private String toWallet;

    @NotNull
    private Long typeId;
}