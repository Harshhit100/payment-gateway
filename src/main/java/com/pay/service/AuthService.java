package com.pay.service;

import com.pay.dto.mapper.SignupRequestMapper;
import com.pay.dto.model.request.LoginRequest;
import com.pay.dto.model.request.SignupRequest;
import com.pay.dto.model.response.CommandResponse;
import com.pay.dto.model.response.JwtResponse;
import com.pay.model.User;
import com.pay.repository.UserRepository;
import com.pay.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pay.utils.JwtUtils.generateJwtToken;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final SignupRequestMapper signupRequestMapper;

    @Value("${app.security.jwtSecret}")
    private String jwtSecret;

    @Value("${app.security.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, SignupRequestMapper signupRequestMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.signupRequestMapper = signupRequestMapper;
    }

    public JwtResponse login(final LoginRequest request) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername().trim(), request.getPassword().trim()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = generateJwtToken(authentication, jwtSecret, jwtExpirationMs);

        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        final List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return JwtResponse
                .builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .roles(roles).build();
    }

    public CommandResponse signup(SignupRequest request) {
        if (userRepository.existsByUsernameIgnoreCase(request.getUsername().trim()))
            throw new RuntimeException();
        if (userRepository.existsByEmailIgnoreCase(request.getEmail().trim()))
            throw new RuntimeException();

        final User user = signupRequestMapper.toEntity(request);
        userRepository.save(user);
        return CommandResponse.builder().id(user.getId()).build();
    }
}
