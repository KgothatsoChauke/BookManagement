package com.springboot.BookApplication.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private String message;
    private String error;
    private LocalDateTime timeStamp;

}
