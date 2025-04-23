package com.djw.DemaLibrary.controller;

import com.djw.DemaLibrary.domain.AuthResponse;
import com.djw.DemaLibrary.domain.dto.LoginRequest;
import com.djw.DemaLibrary.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/auth")
public class AuthController {

    private final AuthenticationService authService;

    public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        UserDetails user = authService.authenticate(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        AuthResponse authResponse = AuthResponse.builder()
                .token(authService.generateToken(user))
                .expiresIn(86400)
                .build();

        return ResponseEntity.ok(authResponse);
    }
}
