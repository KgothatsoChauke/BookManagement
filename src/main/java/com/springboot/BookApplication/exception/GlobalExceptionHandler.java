package com.springboot.BookApplication.exception;

import org.springframework.dao.DataIntegrityViolationException;
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

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
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

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException exception){
        ErrorResponse error = new ErrorResponse();

        error.setTimeStamp(LocalDateTime.now());
        error.setError(HttpStatus.CONFLICT.getReasonPhrase());
        error.setStatusCode(HttpStatus.CONFLICT.value());
        error.setMessage("A book with the same ISBN already exists.");

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex){

        ErrorResponse error = new ErrorResponse();

        error.setTimeStamp(LocalDateTime.now());
        error.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage("An unexpected error occurred.");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}


