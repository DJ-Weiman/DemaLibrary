package com.djw.DemaLibrary.services;

import com.djw.DemaLibrary.domain.dto.BookDto;
import com.djw.DemaLibrary.domain.dto.BorrowingRequest;
import com.djw.DemaLibrary.domain.dto.BorrowingResponse;

import java.util.List;

public interface BorrowingService {
    BorrowingResponse checkAndBorrowBook(BorrowingRequest borrowingRequest);
    void checkAndReturnBook(String bookId);
    List<BookDto> getPastBookings();
}
