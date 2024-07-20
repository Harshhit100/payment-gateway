package com.pay.dto.model.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletResponse {
    private Long id;
    private String name;
    private BigDecimal balance;
    private UserResponse user;
}
