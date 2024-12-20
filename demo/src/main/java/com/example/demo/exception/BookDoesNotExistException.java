package com.example.demo.exception;

public class BookDoesNotExistException extends Exception{
    public BookDoesNotExistException(String message) {
        super(String.format("%s",message));
    }
}
