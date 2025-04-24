package com.djw.DemaLibrary.services;

import com.djw.DemaLibrary.domain.AuthResponse;
import com.djw.DemaLibrary.domain.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    AuthResponse authenticate(String username, String password);
    String generateToken(UserDetails userDetails);
    UserDetails validateToken(String token);
}
