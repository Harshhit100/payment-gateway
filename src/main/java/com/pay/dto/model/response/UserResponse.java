package com.pay.dto.model.response;

import lombok.Data;

import java.util.Set;

@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String fullName;
    private String email;
    private Set<RoleResponse> roles;
}
