package com.pay.dto.mapper;

import com.pay.dto.model.request.SignupRequest;
import com.pay.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-25T11:54:58+0530",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class SignupRequestMapperImpl extends SignupRequestMapper {

    @Override
    public User toEntity(SignupRequest dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );

        user.setFirstName( org.springframework.util.StringUtils.capitalize(dto.getFirstName().toLowerCase()) );
        user.setLastName( org.springframework.util.StringUtils.capitalize(dto.getLastName().toLowerCase()) );
        user.setUsername( dto.getUsername().trim().toLowerCase() );
        user.setEmail( dto.getEmail().trim().toLowerCase() );

        setToEntityFields( user, dto );

        return user;
    }
}
