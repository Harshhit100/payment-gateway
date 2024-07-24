package com.pay.dto.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class JwtResponse {

    private String type;
    private String token;
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private List<String> roles;
}
