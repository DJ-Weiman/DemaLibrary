package com.djw.DemaLibrary.services.impl;

import com.djw.DemaLibrary.domain.entities.UserEntity;
import com.djw.DemaLibrary.exception.ValidationException;
import com.djw.DemaLibrary.repositories.UserRepository;
import com.djw.DemaLibrary.services.UserRegistrationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserEntity registerUser(UserEntity user) {
        final var errors = new HashMap<String, String>();

        if(userRepository.existsByName(user.getName())){
            errors.put("email", "Username [%s] is already taken".formatted(user.getName()));
        }

        if(!errors.isEmpty())
            throw new ValidationException(HttpStatus.CONFLICT, errors);

        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setCreated_at(LocalDateTime.now());

        return userRepository.save(user);
    }
}
