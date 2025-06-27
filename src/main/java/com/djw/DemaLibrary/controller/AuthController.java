package com.djw.DemaLibrary.controller;

import com.djw.DemaLibrary.domain.auth.LoginRequest;
import com.djw.DemaLibrary.domain.auth.LoginResponse;
import com.djw.DemaLibrary.domain.auth.RegisterRequest;
import com.djw.DemaLibrary.domain.auth.RegisterResponse;
import com.djw.DemaLibrary.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/library/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = authService.signInUser(loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        authService.registerUser(registerRequest);
        return ResponseEntity.ok().build();
    }
}
