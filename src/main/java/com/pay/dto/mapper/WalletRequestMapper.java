package com.pay.dto.mapper;

import com.pay.dto.model.request.WalletRequest;
import com.pay.model.Wallet;
import com.pay.service.UserService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",
        uses = {UserService.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class WalletRequestMapper {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Mapping(target = "name", expression = "java(org.apache.commons.text.WordUtils.capitalizeFully(dto.getName()))")
    @Mapping(target = "bankAccountNumber", expression = "java(org.apache.commons.lang3.StringUtils.upperCase(dto.getBankAccountNumber()))")
    @Mapping(target = "user", ignore = true)
    public abstract Wallet toEntity(WalletRequest dto);

    public abstract WalletRequest toDto(Wallet entity);

    @AfterMapping
    void setToEntityFields(@MappingTarget Wallet entity, WalletRequest dto) {
        entity.setUser(userService.getReferenceById(dto.getUserId()));
    }
}