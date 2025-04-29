package com.djw.DemaLibrary.controller;

import com.djw.DemaLibrary.domain.dto.UserDto;
import com.djw.DemaLibrary.domain.entities.UserEntity;
import com.djw.DemaLibrary.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/library/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser(@RequestBody UserDto userDto){
        Optional<UserDto> userDtoOptional = userService.getUserByName(userDto.getName());

        return userDtoOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> savedUsers = userService.getAllUsers();
        return new ResponseEntity<>(savedUsers, HttpStatus.OK);
    }

}
