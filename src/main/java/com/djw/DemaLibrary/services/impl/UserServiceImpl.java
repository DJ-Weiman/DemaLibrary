package com.djw.DemaLibrary.services.impl;

import com.djw.DemaLibrary.domain.dto.UserDto;
import com.djw.DemaLibrary.domain.entities.UserEntity;
import com.djw.DemaLibrary.mappers.Mapper;
import com.djw.DemaLibrary.repositories.UserRepository;
import com.djw.DemaLibrary.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Mapper<UserEntity, UserDto> userMapper;


    @Override
    public List<UserDto> getAllUsers() {
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .map(userMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByName(String userName) {
        UserEntity userEntity = userRepository.findByName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("No User found with name"));

        return UserDto.builder()
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .registered_date(userEntity.getCreated_at().toString())
                .past_borrow_count(5)
                .current_borrow_count(2)
                .remaining_borrow_count(1)
                .build();

    }

//    private int getPastBorrowCount(){
//
//    }
}
