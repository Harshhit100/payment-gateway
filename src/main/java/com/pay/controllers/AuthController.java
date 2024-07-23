package com.pay.controllers;

import com.pay.dto.model.request.LoginRequest;
import com.pay.dto.model.request.SignupRequest;
import com.pay.dto.model.response.ApiResponse;
import com.pay.dto.model.response.CommandResponse;
import com.pay.dto.model.response.JwtResponse;
import com.pay.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.Instant;

import static com.pay.commons.Constants.*;

@RestController
@RequestMapping(AUTH_API)
public class AuthController {

    private final Clock clock;

    private final AuthService authService;

    @Autowired
    public AuthController(Clock clock, AuthService authService) {
        this.clock = clock;
        this.authService = authService;
    }

    @PostMapping(LOGIN)
    public ResponseEntity<ApiResponse<JwtResponse>> login(@RequestBody LoginRequest request) {
        final JwtResponse response = authService.login(request);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));
    }

    @PostMapping(SIGN_UP)
    public ResponseEntity<ApiResponse<CommandResponse>> signup( @RequestBody SignupRequest request) {
        final CommandResponse response = authService.signup(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));
    }
}
