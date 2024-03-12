package com.example.books_managment.services;

import com.example.books_managment.entities.Book;

import java.util.List;

public interface BookService {

    public List<Book> getAllBooks();
    public Book getBookById(Long id);
    public void deleteBook(Long id );
    Book updateBook(Long id, Book b);
    public Book addBook(Book b);
}
