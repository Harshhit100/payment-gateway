package com.pay.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {

    USER("User"),
    ADMIN("Admin");

    private String role;
}
