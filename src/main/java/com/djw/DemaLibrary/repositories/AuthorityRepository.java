package com.djw.DemaLibrary.repositories;

import com.djw.DemaLibrary.domain.entities.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, UUID> {
    Optional<AuthorityEntity> getByAuthorityTitle(String authorityTitle);
}
