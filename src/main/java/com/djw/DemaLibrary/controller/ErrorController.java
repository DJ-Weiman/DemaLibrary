package com.djw.DemaLibrary.controller;

import com.djw.DemaLibrary.domain.dto.ApiErrorResponse;
import com.djw.DemaLibrary.exception.InsufficientBookFieldsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(InsufficientBookFieldsException.class)
    public ResponseEntity<ApiErrorResponse> handleInsufficientBookFieldsException(InsufficientBookFieldsException ex){
        ApiErrorResponse error = ApiErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
