package com.pay.dto.mapper;

import com.pay.dto.model.response.RoleResponse;
import com.pay.dto.model.response.UserResponse;
import com.pay.dto.model.response.WalletResponse;
import com.pay.model.Role;
import com.pay.model.User;
import com.pay.model.Wallet;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-24T12:55:08+0530",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.0.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class WalletResponseMapperImpl implements WalletResponseMapper {

    @Override
    public Wallet toEntity(WalletResponse dto) {
        if ( dto == null ) {
            return null;
        }

        Wallet wallet = new Wallet();

        wallet.setId( dto.getId() );
        wallet.setName( dto.getName() );
        wallet.setBalance( dto.getBalance() );
        wallet.setUser( userResponseToUser( dto.getUser() ) );

        return wallet;
    }

    @Override
    public WalletResponse toDto(Wallet entity) {
        if ( entity == null ) {
            return null;
        }

        WalletResponse walletResponse = new WalletResponse();

        walletResponse.setId( entity.getId() );
        walletResponse.setName( entity.getName() );
        walletResponse.setBalance( entity.getBalance() );
        walletResponse.setUser( userToUserResponse( entity.getUser() ) );

        return walletResponse;
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
        user.setUserName( userResponse.getUserName() );
        user.setEmail( userResponse.getEmail() );
        user.setRoles( roleResponseSetToRoleSet( userResponse.getRoles() ) );

        return user;
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
        userResponse.setUserName( user.getUserName() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setRoles( roleSetToRoleResponseSet( user.getRoles() ) );

        setFullName( userResponse, user );

        return userResponse;
    }
}
