package com.djw.DemaLibrary.services;


import com.djw.DemaLibrary.domain.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto createUser(UserDto user);

    List<UserDto> getAllUsers();

    Optional<UserDto> getUserByName(String userName);

}
