package com.example.demo.services;

import com.example.demo.exception.AuthorAlreadyExistException;
import com.example.demo.exception.BookAlreadyExistsException;
import com.example.demo.exception.BookDoesNotExistException;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.models.Book;

import java.util.List;

public interface BookService {

    Book createBook(Book book) throws BookAlreadyExistsException;

    List<Book> getAllBooks();

    Book getBookById(Long Id) throws BookNotFoundException;

    Book getBookByBookName(String bookName) throws BookDoesNotExistException;

    Book getBookByAuthor(String author) throws BookDoesNotExistException;

    void updateBook(Long Id, Book book) throws BookNotFoundException;

    void deleteBook(Long Id) throws BookNotFoundException;
}
