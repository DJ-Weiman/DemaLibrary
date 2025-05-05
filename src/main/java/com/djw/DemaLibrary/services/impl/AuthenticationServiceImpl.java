package com.djw.DemaLibrary.services.impl;

import com.djw.DemaLibrary.domain.auth.LoginRequest;
import com.djw.DemaLibrary.domain.auth.LoginResponse;
import com.djw.DemaLibrary.domain.auth.RegisterRequest;
import com.djw.DemaLibrary.domain.auth.RegisterResponse;
import com.djw.DemaLibrary.domain.entities.AuthorityEntity;
import com.djw.DemaLibrary.domain.entities.UserEntity;
import com.djw.DemaLibrary.exception.AuthorityNotFoundException;
import com.djw.DemaLibrary.jwt.JwtUtils;
import com.djw.DemaLibrary.repositories.AuthorityRepository;
import com.djw.DemaLibrary.security.LibraryUserDetails;
import com.djw.DemaLibrary.security.LibraryUserDetailsService;
import com.djw.DemaLibrary.services.AuthenticationService;
import com.djw.DemaLibrary.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final LibraryUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @Override
    public LoginResponse signInUser(LoginRequest loginRequest) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        LibraryUserDetails userDetails = (LibraryUserDetails) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());


        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails, roles);

        return LoginResponse.builder()
                .username(userDetails.getUsername())
                .token(jwtToken)
                .build();
    }

    @Override
    public RegisterResponse registerUser(RegisterRequest registerRequest) {
        if(userDetailsService.userExists(registerRequest.getUsername()))
            return null;

        AuthorityEntity authority = authorityRepository.getByAuthorityTitle("ROLE_USER").orElseThrow(() ->
                new AuthorityNotFoundException("Default Authority User Role not found in DB"));

        UserEntity user = UserEntity.builder()
                .name(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .userPassword(passwordEncoder.encode(registerRequest.getPassword()))
                .created_at(LocalDateTime.now())
                .authority(authority)
                .build();

        UserDetails userDetails = userDetailsService.saveUser(user);

        return RegisterResponse.builder()
                .username(userDetails.getUsername())
                .email(registerRequest.getEmail())
                .build();
    }
}
