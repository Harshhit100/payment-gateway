package com.pay.dto.mapper;

import com.pay.dto.model.response.RoleResponse;
import com.pay.dto.model.response.TransactionResponse;
import com.pay.dto.model.response.UserResponse;
import com.pay.dto.model.response.WalletResponse;
import com.pay.model.Role;
import com.pay.model.Transaction;
import com.pay.model.User;
import com.pay.model.Wallet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-25T11:54:58+0530",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class TransactionResponseMapperImpl implements TransactionResponseMapper {

    @Override
    public Transaction toEntity(TransactionResponse dto) {
        if ( dto == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setId( dto.getId() );
        transaction.setAmount( dto.getAmount() );
        transaction.setDescription( dto.getDescription() );
        transaction.setCreatedAt( dto.getCreatedAt() );
        if ( dto.getReferenceNumber() != null ) {
            transaction.setReferenceNumber( UUID.fromString( dto.getReferenceNumber() ) );
        }
        transaction.setFromWallet( walletResponseToWallet( dto.getFromWallet() ) );
        transaction.setToWallet( walletResponseToWallet( dto.getToWallet() ) );

        return transaction;
    }

    @Override
    public TransactionResponse toDto(Transaction entity) {
        if ( entity == null ) {
            return null;
        }

        TransactionResponse transactionResponse = new TransactionResponse();

        transactionResponse.setId( entity.getId() );
        transactionResponse.setAmount( entity.getAmount() );
        transactionResponse.setDescription( entity.getDescription() );
        if ( entity.getReferenceNumber() != null ) {
            transactionResponse.setReferenceNumber( entity.getReferenceNumber().toString() );
        }
        transactionResponse.setFromWallet( walletToWalletResponse( entity.getFromWallet() ) );
        transactionResponse.setToWallet( walletToWalletResponse( entity.getToWallet() ) );

        formatCreatedAt( transactionResponse, entity );

        return transactionResponse;
    }

    protected Role roleResponseToRole(RoleResponse roleResponse) {
        if ( roleResponse == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleResponse.getId() );

        return role;
    }

    protected Set<Role> roleResponseSetToRoleSet(Set<RoleResponse> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new LinkedHashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleResponse roleResponse : set ) {
            set1.add( roleResponseToRole( roleResponse ) );
        }

        return set1;
    }

    protected User userResponseToUser(UserResponse userResponse) {
        if ( userResponse == null ) {
            return null;
        }

        User user = new User();

        user.setId( userResponse.getId() );
        user.setFirstName( userResponse.getFirstName() );
        user.setLastName( userResponse.getLastName() );
        user.setUsername( userResponse.getUsername() );
        user.setEmail( userResponse.getEmail() );
        user.setRoles( roleResponseSetToRoleSet( userResponse.getRoles() ) );

        return user;
    }

    protected Wallet walletResponseToWallet(WalletResponse walletResponse) {
        if ( walletResponse == null ) {
            return null;
        }

        Wallet wallet = new Wallet();

        wallet.setId( walletResponse.getId() );
        wallet.setName( walletResponse.getName() );
        wallet.setBalance( walletResponse.getBalance() );
        wallet.setUser( userResponseToUser( walletResponse.getUser() ) );

        return wallet;
    }

    protected RoleResponse roleToRoleResponse(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleResponse roleResponse = new RoleResponse();

        roleResponse.setId( role.getId() );

        return roleResponse;
    }

    protected Set<RoleResponse> roleSetToRoleResponseSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleResponse> set1 = new LinkedHashSet<RoleResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleResponse( role ) );
        }

        return set1;
    }

    protected UserResponse userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setFirstName( user.getFirstName() );
        userResponse.setLastName( user.getLastName() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setRoles( roleSetToRoleResponseSet( user.getRoles() ) );

        return userResponse;
    }

    protected WalletResponse walletToWalletResponse(Wallet wallet) {
        if ( wallet == null ) {
            return null;
        }

        WalletResponse walletResponse = new WalletResponse();

        walletResponse.setId( wallet.getId() );
        walletResponse.setName( wallet.getName() );
        walletResponse.setBalance( wallet.getBalance() );
        walletResponse.setUser( userToUserResponse( wallet.getUser() ) );

        return walletResponse;
    }
}
