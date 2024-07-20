package com.pay.dto.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private Long timeStamp;
    private String message;
    private T data;
}
