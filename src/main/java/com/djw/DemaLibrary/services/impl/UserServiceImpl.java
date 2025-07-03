package com.djw.DemaLibrary.services.impl;

import com.djw.DemaLibrary.domain.dto.UserDto;
import com.djw.DemaLibrary.domain.entities.BorrowingEntity;
import com.djw.DemaLibrary.domain.entities.UserEntity;
import com.djw.DemaLibrary.mappers.Mapper;
import com.djw.DemaLibrary.repositories.BorrowingRepository;
import com.djw.DemaLibrary.repositories.UserRepository;
import com.djw.DemaLibrary.services.UserService;
import com.djw.DemaLibrary.utils.Constants;
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
    private final BorrowingRepository borrowingRepository;
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

        List<BorrowingEntity> borrowingEntities = borrowingRepository.getBorrowingsForUser(userEntity.getId());

        int currentBorrowCount = borrowingEntities.stream()
                .filter(borrowing -> borrowing.getReturned_at() == null)
                .toList()
                .size();

        return UserDto.builder()
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .registered_date(userEntity.getCreated_at().toLocalDate().toString())
                .past_borrow_count(borrowingEntities.size())
                .current_borrow_count(currentBorrowCount)
                .remaining_borrow_count(Constants.BORROWING_LIMIT - currentBorrowCount)
                .build();
    }

}
