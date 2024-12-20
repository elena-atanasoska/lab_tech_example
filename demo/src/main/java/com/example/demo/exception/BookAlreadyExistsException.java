package com.example.demo.exception;

public class BookAlreadyExistsException extends Exception{
    public BookAlreadyExistsException(String message){
        super(String.format("%s",message));
    }
}
