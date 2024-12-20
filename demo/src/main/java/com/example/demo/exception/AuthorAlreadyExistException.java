package com.example.demo.exception;

public class AuthorAlreadyExistException extends Exception{
    public AuthorAlreadyExistException(String message) {
        super(String.format("%s",message));
    }
}
