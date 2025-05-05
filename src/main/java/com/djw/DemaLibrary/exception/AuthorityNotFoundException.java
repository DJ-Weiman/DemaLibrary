package com.djw.DemaLibrary.exception;

public class AuthorityNotFoundException extends RuntimeException{
    public AuthorityNotFoundException(String message) {
        super(message);
    }
}
