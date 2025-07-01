package com.djw.DemaLibrary.services.impl;

import com.djw.DemaLibrary.domain.dto.BookDto;
import com.djw.DemaLibrary.domain.entities.BookEntity;
import com.djw.DemaLibrary.exception.InsufficientBookFieldsException;
import com.djw.DemaLibrary.mappers.Mapper;
import com.djw.DemaLibrary.repositories.BookRepository;
import com.djw.DemaLibrary.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    public Page<BookDto> getAllBooks(Pageable pageable) {
        Page<BookEntity> bookEntityPage = bookRepository.findAll(pageable);

        return  bookEntityPage.map(bookMapper::mapTo);
    }

    @Override
    public Optional<BookDto> getBookById(String id) {
        Optional<BookEntity> bookOptional = bookRepository.getBookById(UUID.fromString(id));
        return bookOptional.map(bookMapper::mapTo);
    }

    @Override
    public List<BookDto> getAllAvailableBooks() {
        List<BookEntity> availableBooks = bookRepository.findAvailableBooks();

        return availableBooks
                .stream()
                .map(bookMapper::mapTo)
                .toList();

//        return StreamSupport
//                .stream(availableBookIterable.spliterator(), false)
//                .map(bookMapper::mapTo)
//                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBooksByAuthor(String authorName) {
        List<BookEntity> booksByAuthor = bookRepository.getBooksByAuthor(authorName);

        return booksByAuthor
                .stream()
                .map(bookMapper::mapTo)
                .toList();
    }

    @Override
    public List<BookDto> getBooksPublishedInYear(int publishedYear) {
        List<BookEntity> booksPublishedInYear = bookRepository.getBooksPublishedInYear(publishedYear);

        return booksPublishedInYear
                .stream()
                .map(bookMapper::mapTo)
                .toList();
    }


}
