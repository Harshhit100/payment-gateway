package com.pay.dto.mapper;

import com.pay.dto.model.request.TransactionRequest;
import com.pay.model.Transaction;
import com.pay.service.TypeService;
import com.pay.service.WalletService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring",
        uses = {WalletService.class, TypeService.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class TransactionRequestMapper {

    private WalletService walletService;
    private TypeService typeService;

    @Autowired
    public void setWalletService(@Lazy WalletService walletService) {
        this.walletService = walletService;
    }

    @Autowired
    public void setTypeService(@Lazy TypeService typeService) {
        this.typeService = typeService;
    }

    @Mapping(target = "status", expression = "java(com.pay.model.Status.SUCCESS)")
    @Mapping(target = "referenceNumber", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(source = "createdAt", target = "createdAt", defaultExpression = "java(java.time.Instant.now())")
    @Mapping(target = "fromWallet", ignore = true)
    @Mapping(target = "toWallet", ignore = true)
    @Mapping(target = "type", ignore = true)
    public abstract Transaction toEntity(TransactionRequest dto);

    public abstract TransactionRequest toDto(Transaction entity);

    @AfterMapping
    void setToEntityFields(@MappingTarget Transaction entity, TransactionRequest dto) {
        entity.setFromWallet(walletService.getByBankAccountNumber(dto.getFromWalletBankAccountNumber()));
        entity.setToWallet(walletService.getByBankAccountNumber(dto.getToWalletBankAccountNumber()));
        entity.setType(typeService.getReferenceById(dto.getTypeId()));
    }
}
