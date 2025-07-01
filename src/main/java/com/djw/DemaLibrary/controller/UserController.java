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

    @GetMapping("/me/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable("username") String username){
        UserDto user = userService.getUserByName(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> savedUsers = userService.getAllUsers();
        return new ResponseEntity<>(savedUsers, HttpStatus.OK);
    }

}
