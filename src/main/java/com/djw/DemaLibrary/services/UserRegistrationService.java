package com.djw.DemaLibrary.services;


import com.djw.DemaLibrary.domain.entities.UserEntity;

public interface UserRegistrationService {
    UserEntity registerUser(UserEntity userDetails);
}
