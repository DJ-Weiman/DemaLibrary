package com.djw.DemaLibrary.exception;

public class UserExceededBorrowingLimitException extends RuntimeException {
    public UserExceededBorrowingLimitException(String message) {
        super(message);
    }
}
