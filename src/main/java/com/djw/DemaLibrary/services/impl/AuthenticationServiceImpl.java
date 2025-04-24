package com.djw.DemaLibrary.services.impl;

import com.djw.DemaLibrary.domain.AuthResponse;
import com.djw.DemaLibrary.services.AuthenticationService;
import com.djw.DemaLibrary.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private final Long jwtExpiryMs = 86400000L;

    @Override
    public AuthResponse authenticate(String username, String password) {
        final var authToken = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        final var authentication = authenticationManager.authenticate(authToken);

        final var token = jwtService.generateToken(username);

        return new AuthResponse(token, jwtExpiryMs);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return "";
    }

    @Override
    public UserDetails validateToken(String token) {
        return null;
    }
}
