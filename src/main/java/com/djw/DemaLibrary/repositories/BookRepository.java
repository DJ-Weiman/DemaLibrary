package com.djw.DemaLibrary.repositories;

import com.djw.DemaLibrary.domain.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {

    Optional<BookEntity> getBookById(UUID bookId);

    List<BookEntity> getBooksByAuthor(String author);

    @Query("SELECT b FROM BookEntity b WHERE b.available_copies > 0")
    List<BookEntity> findAvailableBooks();

    @Query("SELECT b FROM BookEntity b WHERE b.published_year = ?1")
    List<BookEntity> getBooksPublishedInYear(int publishedYear);

    @Query("SELECT b.available_copies FROM BookEntity b WHERE b.id = ?1")
    Optional<Integer> getAvailableCopiesOfBook(UUID bookId);

    @Query(value = "SELECT b FROM BookEntity b WHERE LOWER(b.title) LIKE %:param% OR LOWER(b.author) LIKE %:param%")
    Page<BookEntity> getBooksBySearchParam(@Param("param") String param, Pageable pageable);
}
