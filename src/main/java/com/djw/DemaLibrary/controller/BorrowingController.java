package com.djw.DemaLibrary.controller;

import com.djw.DemaLibrary.domain.dto.BookDto;
import com.djw.DemaLibrary.domain.dto.BorrowingRequest;
import com.djw.DemaLibrary.domain.dto.BorrowingResponse;
import com.djw.DemaLibrary.services.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/library")
@RequiredArgsConstructor
public class BorrowingController {
    private final BorrowingService borrowingService;

    @PostMapping("/borrowing")
    public ResponseEntity<BorrowingResponse> borrowBook(@RequestBody BorrowingRequest borrowingRequest){
        BorrowingResponse response = borrowingService.checkAndBorrowBook(borrowingRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/return/{bookId}")
    public ResponseEntity<?> returnBook(@PathVariable String bookId){
        borrowingService.checkAndReturnBook(bookId);
        return ResponseEntity.ok("Book Returned Successfully");
    }

    @GetMapping("/pastBookings")
    public ResponseEntity<List<BookDto>> getPastBookingsForUser(){
        List<BookDto> pastBookings = borrowingService.getPastBookings();
        return new ResponseEntity<>(pastBookings, HttpStatus.OK);
    }

}
