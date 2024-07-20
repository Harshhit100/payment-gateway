package com.pay.dto.model.response;

import lombok.Data;

import java.util.Set;

@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String LastName;
    private String userName;
    private String lastName;
    private String email;
    private Set<RoleResponse> roles;
}
