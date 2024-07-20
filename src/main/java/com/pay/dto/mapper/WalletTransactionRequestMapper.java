package com.pay.dto.mapper;

import com.pay.dto.model.request.TransactionRequest;
import com.pay.dto.model.request.WalletRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WalletTransactionRequestMapper {

    @Mapping(target = "amount", source = "balance")
    @Mapping(target = "description", constant = "Initial balance")
    @Mapping(target = "fromWalletBankAccountNumber", source = "iban")
    @Mapping(target = "toWalletBankAccountNumber", source = "iban")
    @Mapping(target = "typeId", constant = "1L")
    TransactionRequest toTransactionDto(WalletRequest entity);

    WalletRequest toWalletDto(TransactionRequest dto);
}
