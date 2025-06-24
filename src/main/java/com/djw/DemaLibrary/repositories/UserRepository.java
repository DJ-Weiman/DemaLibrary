package com.djw.DemaLibrary.repositories;

import com.djw.DemaLibrary.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> getUserById(UUID userId);

    Optional<UserEntity> findByName(String name);

    boolean existsByName(String username);
    boolean existsByEmail(String email);
}
