package com.djw.DemaLibrary.services;

import com.djw.DemaLibrary.domain.dto.BorrowingRequest;
import com.djw.DemaLibrary.domain.dto.BorrowingResponse;
import com.djw.DemaLibrary.domain.dto.BorrowingDTO;

import java.util.List;

public interface BorrowingService {
    BorrowingResponse checkAndBorrowBook(BorrowingRequest borrowingRequest);
    void checkAndReturnBook(String bookId);
    List<BorrowingDTO> getPastBookings();
}
