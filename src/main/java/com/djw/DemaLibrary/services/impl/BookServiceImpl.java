package com.djw.DemaLibrary.services.impl;

import com.djw.DemaLibrary.domain.dto.BookDto;
import com.djw.DemaLibrary.domain.entities.BookEntity;
import com.djw.DemaLibrary.exception.InsufficientBookFieldsException;
import com.djw.DemaLibrary.mappers.Mapper;
import com.djw.DemaLibrary.repositories.BookRepository;
import com.djw.DemaLibrary.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final Mapper<BookEntity, BookDto> bookMapper;

    @Override
    public BookDto createBook(BookDto bookDto) {
        if(bookDto.getTitle() == null || bookDto.getAuthor() == null)
            throw new InsufficientBookFieldsException("Insufficient Data for Book Entity");

        BookEntity savedBook = bookRepository.save(bookMapper.mapFrom(bookDto));

        return bookMapper.mapTo(savedBook);
    }

    @Override
    public List<BookDto> getAllBooks() {
        Iterable<BookEntity> allBooksIterable = bookRepository.findAll();

        return StreamSupport
                .stream(allBooksIterable.spliterator(), false)
                .map(bookMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getAllAvailableBooks() {
        Iterable<BookEntity> availableBookIterable = bookRepository.findAvailableBooks();

        return StreamSupport
                .stream(availableBookIterable.spliterator(), false)
                .map(bookMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBooksByAuthor(String authorName) {
        Iterable<BookEntity> booksByAuthorIterable = bookRepository.getBooksByAuthor(authorName);

        return StreamSupport
                .stream(booksByAuthorIterable.spliterator(), false)
                .map(bookMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBooksPublishedInYear(int publishedYear) {
        Iterable<BookEntity> booksPublishedInYearIterable = bookRepository.getBooksPublishedInYear(publishedYear);

        return StreamSupport
                .stream(booksPublishedInYearIterable.spliterator(), false)
                .map(bookMapper::mapTo)
                .collect(Collectors.toList());
    }


}
