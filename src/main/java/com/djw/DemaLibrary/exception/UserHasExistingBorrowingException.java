package com.djw.DemaLibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserHasExistingBorrowingException extends RuntimeException {
    public UserHasExistingBorrowingException(String message) {
        super(message);
    }
}
