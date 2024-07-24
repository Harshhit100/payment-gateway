package com.pay.dto.mapper;

import com.pay.dto.model.request.WalletRequest;
import com.pay.model.Wallet;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-24T12:55:08+0530",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.0.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class WalletRequestMapperImpl extends WalletRequestMapper {

    @Override
    public Wallet toEntity(WalletRequest dto) {
        if ( dto == null ) {
            return null;
        }

        Wallet wallet = new Wallet();

        wallet.setId( dto.getId() );
        wallet.setBalance( dto.getBalance() );

        wallet.setName( org.springframework.util.StringUtils.capitalize(dto.getName().toLowerCase()) );
        wallet.setBankAccountNumber( dto.getBankAccountNumber().toUpperCase() );

        setToEntityFields( wallet, dto );

        return wallet;
    }

    @Override
    public WalletRequest toDto(Wallet entity) {
        if ( entity == null ) {
            return null;
        }

        WalletRequest walletRequest = new WalletRequest();

        walletRequest.setId( entity.getId() );
        walletRequest.setBankAccountNumber( entity.getBankAccountNumber() );
        walletRequest.setName( entity.getName() );
        walletRequest.setBalance( entity.getBalance() );

        return walletRequest;
    }
}
