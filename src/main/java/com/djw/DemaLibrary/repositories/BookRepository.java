package com.djw.DemaLibrary.repositories;

import com.djw.DemaLibrary.domain.entities.BookEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BookRepository extends CrudRepository<BookEntity, UUID> {

    @Query("SELECT b FROM BookEntity b WHERE b.available_copies > 0")
    Iterable<BookEntity> findAvailableBooks();

    @Query("SELECT b FROM BookEntity b WHERE b.author = ?1")
    Iterable<BookEntity> getBooksByAuthor(String authorName);

    @Query("SELECT b FROM BookEntity b WHERE b.published_year = ?1")
    Iterable<BookEntity> getBooksPublishedInYear(String publishedYear);
}
