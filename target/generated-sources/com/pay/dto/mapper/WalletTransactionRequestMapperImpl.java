package com.pay.dto.mapper;

import com.pay.dto.model.request.TransactionRequest;
import com.pay.dto.model.request.WalletRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-24T12:55:08+0530",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.0.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class WalletTransactionRequestMapperImpl implements WalletTransactionRequestMapper {

    @Override
    public TransactionRequest toTransactionDto(WalletRequest entity) {
        if ( entity == null ) {
            return null;
        }

        TransactionRequest transactionRequest = new TransactionRequest();

        transactionRequest.setAmount( entity.getBalance() );
        transactionRequest.setFromWalletBankAccountNumber( entity.getBankAccountNumber() );
        transactionRequest.setToWalletBankAccountNumber( entity.getBankAccountNumber() );
        transactionRequest.setId( entity.getId() );

        transactionRequest.setDescription( "Initial balance" );
        transactionRequest.setTypeId( (long) 1L );

        return transactionRequest;
    }

    @Override
    public WalletRequest toWalletDto(TransactionRequest dto) {
        if ( dto == null ) {
            return null;
        }

        WalletRequest walletRequest = new WalletRequest();

        walletRequest.setId( dto.getId() );

        return walletRequest;
    }
}
