package com.djw.DemaLibrary.services;


import com.djw.DemaLibrary.domain.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    List<UserDto> getAllUsers();

}
