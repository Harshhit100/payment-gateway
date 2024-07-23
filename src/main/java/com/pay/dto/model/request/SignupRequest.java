package com.pay.dto.model.request;

import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
}
