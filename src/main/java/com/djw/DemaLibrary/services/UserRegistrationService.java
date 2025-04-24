package com.djw.DemaLibrary.services;


import com.djw.DemaLibrary.domain.entities.UserEntity;
import com.djw.DemaLibrary.security.LibraryUserDetails;

public interface UserRegistrationService {
    UserEntity registerUser(UserEntity userDetails);
}
