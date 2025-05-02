package com.djw.DemaLibrary.controller;

import com.djw.DemaLibrary.domain.dto.BookDto;
import com.djw.DemaLibrary.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/library/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping()
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto book){
        BookDto savedBook = bookService.createBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<BookDto>> getAllBooks(){
        List<BookDto> allBooks = bookService.getAllBooks();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookDto>> getAllAvailableBooks(){
        List<BookDto> availableBooks = bookService.getAllAvailableBooks();
        return new ResponseEntity<>(availableBooks, HttpStatus.OK);
    }

    @GetMapping(path = "/{authorName}")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable("authorName") String authorName){
        List<BookDto> booksByAuthor = bookService.getBooksByAuthor(authorName);
        return new ResponseEntity<>(booksByAuthor, HttpStatus.OK);
    }

    @GetMapping(path = "/{publishedYear}")
    public ResponseEntity<List<BookDto>> getBooksFromYear(@PathVariable("publishedYear") int publishedYear){
        List<BookDto> booksFromPublishedYear = bookService.getBooksPublishedInYear(publishedYear);
        return new ResponseEntity<>(booksFromPublishedYear, HttpStatus.OK);
    }
}
