package com.djw.DemaLibrary.controller;

import com.djw.DemaLibrary.domain.dto.BorrowingRequest;
import com.djw.DemaLibrary.domain.dto.BorrowingResponse;
import com.djw.DemaLibrary.services.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/library/borrowing")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService;

    @PostMapping()
    public ResponseEntity<BorrowingResponse> borrowBook(@RequestBody BorrowingRequest borrowingRequest){
        BorrowingResponse response = borrowingService.checkAndBorrowBook(borrowingRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
