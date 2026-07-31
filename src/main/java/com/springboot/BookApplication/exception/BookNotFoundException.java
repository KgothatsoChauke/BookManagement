package com.springboot.BookApplication.exception;

public class BookNotFoundException extends  RuntimeException{
    public BookNotFoundException(String message){
        super(message);
    }
}
