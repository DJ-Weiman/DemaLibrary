package com.djw.DemaLibrary.services;


import com.djw.DemaLibrary.domain.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserByName(String userName);

}
