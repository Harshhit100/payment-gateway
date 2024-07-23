package com.pay.dto.mapper;

import com.pay.dto.model.request.TransactionRequest;
import com.pay.dto.model.request.WalletRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletTransactionRequestMapper {

    @Mapping(target = "amount", source = "balance")
    @Mapping(target = "description", constant = "Initial balance")
    @Mapping(target = "fromWalletBankAccountNumber", source = "bankAccountNumber")
    @Mapping(target = "toWalletBankAccountNumber", source = "bankAccountNumber")
    @Mapping(target = "typeId", constant = "1L")
    TransactionRequest toTransactionDto(WalletRequest entity);

    WalletRequest toWalletDto(TransactionRequest dto);
}
