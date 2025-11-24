package com.example.Quiz_App.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateMobileNumberException.class)
    public ResponseEntity<?> handleDuplicateMobile(DuplicateMobileNumberException ex){
        return ResponseEntity.badRequest().body("Error : " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllException(Exception e){
        return ResponseEntity.badRequest().body("Error : " + e.getMessage());
    }

}
