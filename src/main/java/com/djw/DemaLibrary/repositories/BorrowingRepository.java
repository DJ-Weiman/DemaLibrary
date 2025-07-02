package com.djw.DemaLibrary.repositories;

import com.djw.DemaLibrary.domain.entities.BookEntity;
import com.djw.DemaLibrary.domain.entities.BorrowingEntity;
import com.djw.DemaLibrary.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BorrowingRepository extends JpaRepository<BorrowingEntity, UUID> {

    @Query("SELECT b FROM BorrowingEntity b WHERE b.user.id=?1 AND b.book.id=?2 AND b.returned_at IS NULL")
    List<BorrowingEntity> getOngoingBorrowingForUserAndBook(UUID userID, UUID bookID);

    @Query("SELECT COUNT(id) FROM BorrowingEntity b WHERE b.user.id=?1 AND b.returned_at IS NULL")
    int getCurrentBorrowCountForUser(UUID userID);

    BorrowingEntity getByUserAndBook(UserEntity user, BookEntity book);
}