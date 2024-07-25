package com.pay.dto.model.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TransactionResponse {
    private Long id;
    private BigDecimal amount;
    private String description;
    private String createdAt;
    private String referenceNumber;
    private WalletResponse fromWallet;
    private WalletResponse toWallet;
    private TypeResponse typeResponse;
}
