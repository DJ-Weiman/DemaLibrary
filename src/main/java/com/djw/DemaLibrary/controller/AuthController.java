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
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse;
        try {
            loginResponse = authService.signInUser(loginRequest);
        }catch (AuthenticationException exception){
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad Credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        RegisterResponse registerResponse = authService.registerUser(registerRequest);

        if(registerResponse == null){
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad Credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(registerResponse);
    }
}
