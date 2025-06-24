package com.djw.DemaLibrary.exception;

public class UsernameNotUniqueException extends RuntimeException {
    public UsernameNotUniqueException(String message) {
        super(message);
    }
}
