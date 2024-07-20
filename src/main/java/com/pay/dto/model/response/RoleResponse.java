package com.pay.dto.model.response;

import com.pay.model.RoleType;
import lombok.Data;

@Data
public class RoleResponse {
    private Long id;
    private RoleType roleType;
}
