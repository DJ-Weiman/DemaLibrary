package com.djw.DemaLibrary.services;

import com.djw.DemaLibrary.domain.dto.BorrowingRequest;
import com.djw.DemaLibrary.domain.dto.BorrowingResponse;

public interface BorrowingService {
    BorrowingResponse checkAndBorrowBook(BorrowingRequest borrowingRequest);
}
