package com.example.demo.exception;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(String message){
        super(String.format("%s",message));
    }
}
