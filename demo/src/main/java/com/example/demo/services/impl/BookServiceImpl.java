package com.example.demo.services.impl;

import com.example.demo.exception.AuthorAlreadyExistException;
import com.example.demo.exception.BookAlreadyExistsException;
import com.example.demo.exception.BookDoesNotExistException;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.models.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public Book createBook(Book book) throws BookAlreadyExistsException {
        Optional<Book> existingBook = bookRepository.findBooksByBookName(book.getBookName());
        if (existingBook.isPresent()) {
            throw new BookAlreadyExistsException("Book already exists");
        }
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long Id) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(Id);
        if (book.isEmpty()) {
            throw new BookNotFoundException("Book not found");
        }
        return book.get();
    }

    @Override
    public Book getBookByBookName(String bookName) throws BookDoesNotExistException {
        Optional<Book> book = bookRepository.findBooksByBookName(bookName);
        if (book.isEmpty()) {
            throw new BookDoesNotExistException("The book you are looking for does not exist");
        }
        return book.get();
    }

    @Override
    public Book getBookByAuthor(String author) throws BookDoesNotExistException {
        Optional<Book> book = bookRepository.findBooksByAuthor(author);
        if (book.isEmpty()) {
            throw new BookDoesNotExistException("No books by this author exist");
        }
        return book.get();
    }

    @Override
    public void updateBook(Long Id, Book book) throws BookNotFoundException {
        Optional<Book> book1 = bookRepository.findById(Id);
        if (book1.isEmpty()) {
            throw new BookNotFoundException("The book does not exist");
        }
        Book book2 = book1.get();
        book2.setBookName(book.getBookName());
        book2.setAuthor(book.getAuthor());
        book2.setIsInStock(book.getIsInStock());
        book2.setPrice(book.getPrice());
        bookRepository.save(book2);

    }

    @Override
    public void deleteBook(Long Id) throws BookNotFoundException {

        Optional<Book> book1 = bookRepository.findById(Id);
        if (book1.isEmpty()) {
            throw new BookNotFoundException("The book does not exist");
        }
        bookRepository.delete(book1.get());
    }
}
