package com.djw.DemaLibrary.repositories;

import com.djw.DemaLibrary.domain.entities.BorrowingEntity;
import com.djw.DemaLibrary.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BorrowingRepository extends JpaRepository<BorrowingEntity, UUID> {

    boolean existsByUserAndReturnDateIsNull(UserEntity user);
}
