package com.djw.DemaLibrary.services;

import com.djw.DemaLibrary.domain.auth.LoginRequest;
import com.djw.DemaLibrary.domain.auth.LoginResponse;
import com.djw.DemaLibrary.domain.auth.RegisterRequest;
import com.djw.DemaLibrary.domain.auth.RegisterResponse;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationService {
    LoginResponse signInUser(LoginRequest loginRequest) throws AuthenticationException;
    void registerUser(RegisterRequest registerRequest);
}
