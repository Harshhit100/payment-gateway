package com.pay.dto.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletRequest {

    private Long id;

    private String bankAccountNumber;

    private String name;

    private BigDecimal balance;

    private Long userId;


}
