package com.example.books_managment.services;

import com.example.books_managment.entities.Author;
import com.example.books_managment.entities.Book;
import com.example.books_managment.exceptions.NotFoundException;
import com.example.books_managment.repository.AuthorRepository;
import com.example.books_managment.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new NotFoundException("Book not found with id: " + id);
        }
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setDescription(updatedBook.getDescription());
            // Ajouter d'autres mises à jour d'attributs si nécessaire
            return bookRepository.save(existingBook);
        } else {
            throw new NotFoundException("Book not found with id: " + id);
        }
    }

    @Override
    public void deleteBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new NotFoundException("Book not found with id: " + id);
        }
    }


}
