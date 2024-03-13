package com.example.books_managment.controllers;

import com.example.books_managment.entities.Book;
import com.example.books_managment.exceptions.NotFoundException;
import com.example.books_managment.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Get a book by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Add a new book
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid  @RequestBody Book book) {
        Book createdBook = bookService.addBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    // Update a book
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id,@Valid @RequestBody Book book) {
        try {
            Book updatedBook = bookService.updateBook(id, book);
            return ResponseEntity.ok(updatedBook);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
