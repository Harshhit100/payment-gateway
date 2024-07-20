package com.pay.dto.mapper;

import com.pay.dto.model.response.UserResponse;
import com.pay.dto.model.response.WalletResponse;
import com.pay.model.User;
import com.pay.model.Wallet;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WalletResponseMapper {

    Wallet toEntity(WalletResponse dto);

    WalletResponse toDto(Wallet entity);

    @AfterMapping
    default void setFullName(@MappingTarget UserResponse dto, User entity) {
        dto.setFullName(String.format("%s %s", entity.getFirstName(), entity.getLastName()));
    }
}