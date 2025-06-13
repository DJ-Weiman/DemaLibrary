package com.djw.DemaLibrary.exception;

public class UserNotBorrowedBookException extends RuntimeException {
    public UserNotBorrowedBookException(String message) {
        super(message);
    }
}
