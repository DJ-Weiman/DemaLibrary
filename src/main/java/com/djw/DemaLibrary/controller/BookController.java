package com.djw.DemaLibrary.controller;

import com.djw.DemaLibrary.domain.dto.BookDto;
import com.djw.DemaLibrary.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/library/books")
@RequiredArgsConstructor
@EnableMethodSecurity
public class BookController {

    private final BookService bookService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid BookDto book){
        BookDto savedBook = bookService.createBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable String id){
        Optional<BookDto> retrievedBookOptional = bookService.getBookById(id);

        return retrievedBookOptional.map(bookDto -> new ResponseEntity<>(bookDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping()
    public ResponseEntity<Page<BookDto>> getAllBooks(Pageable pageable){
        Page<BookDto> allBooks = bookService.getAllBooks(pageable);
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookDto>> getAllAvailableBooks(){
        List<BookDto> availableBooks = bookService.getAllAvailableBooks();
        return new ResponseEntity<>(availableBooks, HttpStatus.OK);
    }

    @GetMapping(path = "author/{authorName}")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable("authorName") String authorName){
        List<BookDto> booksByAuthor = bookService.getBooksByAuthor(authorName);
        return new ResponseEntity<>(booksByAuthor, HttpStatus.OK);
    }

    @GetMapping(path = "publishedYear/{publishedYear}")
    public ResponseEntity<List<BookDto>> getBooksFromYear(@PathVariable("publishedYear") int publishedYear){
        List<BookDto> booksFromPublishedYear = bookService.getBooksPublishedInYear(publishedYear);
        return new ResponseEntity<>(booksFromPublishedYear, HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<Page<BookDto>> getBooksForSearchParam(@RequestParam("searchParam") String searchParam, Pageable page){
        Page<BookDto> booksFromPublishedYear = bookService.getBooksForSearchParam(searchParam, page);
        return new ResponseEntity<>(booksFromPublishedYear, HttpStatus.OK);
    }
}
