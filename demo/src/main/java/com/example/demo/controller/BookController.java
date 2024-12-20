package com.example.demo.controller;

import com.example.demo.exception.AuthorAlreadyExistException;
import com.example.demo.exception.BookAlreadyExistsException;
import com.example.demo.exception.BookDoesNotExistException;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.models.Book;
import com.example.demo.services.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/books")
@Validated
@CrossOrigin(origins="*")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long id) throws BookNotFoundException {
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping(value = "/book")
    public ResponseEntity<?> getBookByBookName(@Valid @RequestParam(value = "bookName") @Size(min = 1, message = "Book name must be at least 1 character") String bookName) throws BookDoesNotExistException {
        Book book = bookService.getBookByBookName(bookName);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getBookByAuthorsName(@Valid @RequestParam(value = "author") @Size(min = 2, message = "Author name must be at least 2 characters") String author) throws BookDoesNotExistException {
        Book book = bookService.getBookByAuthor(author);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) throws BookAlreadyExistsException {
        Book newBook = bookService.createBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<?> updateBook(@PathVariable(value = "id") Long id, @Valid @RequestBody Book book) throws BookNotFoundException {
        bookService.updateBook(id, book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long id) throws BookNotFoundException {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
