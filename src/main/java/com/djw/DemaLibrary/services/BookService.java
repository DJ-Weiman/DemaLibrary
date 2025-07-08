package com.djw.DemaLibrary.services;


import com.djw.DemaLibrary.domain.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookDto createBook(BookDto bookDto);
    List<BookDto> getAllAvailableBooks();
    Page<BookDto> getAllBooks(Pageable pageable);
    Optional<BookDto> getBookById(String id);
    List<BookDto> getBooksByAuthor(String authorName);
    List<BookDto> getBooksPublishedInYear(int publishedYear);
    Page<BookDto> getBooksForSearchParam(String searchParam, Pageable pageable);
}
