package com.example.books_managment.controllers;

import com.example.books_managment.entities.Author;
import com.example.books_managment.entities.Book;
import com.example.books_managment.exceptions.ErrorResponse;
import com.example.books_managment.exceptions.NotFoundException;
import com.example.books_managment.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // Method to handle errors and return a ResponseEntity containing an ErrorResponse object
    private ResponseEntity<Object> handleError(HttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse(status, message);
        return ResponseEntity.status(status).body(errorResponse);
    }

    // Get all authors with their books
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        // Supprimer les références circulaires pour éviter une boucle infinie lors de la sérialisation JSON
        authors.forEach(author -> author.getBooks().forEach(book -> book.setAuthor(null)));
        return ResponseEntity.ok(authors);
    }

    // Get an author by ID with their books
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        try {
            Author author = authorService.getAuthorById(id);
            return ResponseEntity.ok(author);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Add a new author
    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        Author createdAuthor = authorService.addAuthor(author);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    // Update an author (avoiding modifying associated books)
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author updatedAuthor) {
        Author author = authorService.updateAuthor(id, updatedAuthor);
        if (author != null) {
            return ResponseEntity.ok(author);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an author (with checking associated books)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable Long id) {
        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return handleError(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // Get books of an author by ID
    @GetMapping("/{authorId}/books")
    public ResponseEntity<List<Book>> getBooksByAuthorId(@PathVariable Long authorId) {
        List<Book> books = authorService.getBooksByAuthorId(authorId);
        return ResponseEntity.ok(books);
    }
}
