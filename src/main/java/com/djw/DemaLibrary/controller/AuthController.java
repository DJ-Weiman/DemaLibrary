package com.djw.DemaLibrary.controller;

import com.djw.DemaLibrary.domain.AuthResponse;
import com.djw.DemaLibrary.domain.dto.LoginRequest;
import com.djw.DemaLibrary.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){

        return ResponseEntity.ok(authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword()));

//        UserDetails user = authService.authenticate(
//                loginRequest.getUsername(),
//                loginRequest.getPassword()
//        );
//
//        AuthResponse authResponse = AuthResponse.builder()
//                .token(authService.generateToken(user))
//                .expiresIn(86400)
//                .build();
//
//        return ResponseEntity.ok(authResponse);
    }
}
