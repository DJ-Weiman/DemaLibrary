package com.djw.DemaLibrary.controller;

import com.djw.DemaLibrary.domain.dto.UserDto;
import com.djw.DemaLibrary.domain.entities.UserEntity;
import com.djw.DemaLibrary.mappers.impl.UserMapper;
import com.djw.DemaLibrary.services.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;
    private final UserMapper userMapper;


    //Change this to an appropriate DTO
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody final UserEntity userDto){
        final var registeredUser = userRegistrationService.registerUser(userDto);

        return ResponseEntity.ok(userMapper.mapTo(registeredUser));
    }
}
