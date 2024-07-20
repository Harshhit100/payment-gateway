package com.pay.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum Status {
    SUCCESS("Success"),
    PENDING("Pending"),
    FAILURE("Failure");

    private String status;

}
