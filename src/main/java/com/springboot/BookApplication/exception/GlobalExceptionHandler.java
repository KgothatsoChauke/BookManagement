package com.springboot.BookApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFound(BookNotFoundException exception){
        ErrorResponse error = new ErrorResponse();

        error.setTimeStamp(LocalDateTime.now());
        error.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotValid(MethodArgumentNotValidException exception) {

        ErrorResponse error = new ErrorResponse();

        error.setTimeStamp(LocalDateTime.now());
        error.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());

        Map<String, String> validationMessages = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError ->
                validationMessages.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        error.setValidationErrors(validationMessages);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }
}


