package com.djw.DemaLibrary.repositories;

import com.djw.DemaLibrary.domain.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {

    Optional<BookEntity> getBookById(UUID bookId);

    @Query("SELECT b FROM BookEntity b WHERE b.available_copies > 0")
    Iterable<BookEntity> findAvailableBooks();

    @Query("SELECT b FROM BookEntity b WHERE b.author = ?1")
    Iterable<BookEntity> getBooksByAuthor(String authorName);

    @Query("SELECT b FROM BookEntity b WHERE b.published_year = ?1")
    Iterable<BookEntity> getBooksPublishedInYear(int publishedYear);

    @Query("SELECT b.available_copies FROM BookEntity b WHERE b.id = ?1")
    Optional<Integer> getAvailableCopiesOfBook(UUID bookId);
}
