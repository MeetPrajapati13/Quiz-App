package com.example.Quiz_App.handler;

public class DuplicateMobileNumberException extends RuntimeException{
    public DuplicateMobileNumberException(String message){
        super(message);
    }
}
