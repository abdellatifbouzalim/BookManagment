package com.example.books_managment.services;


import com.example.books_managment.entities.Author;
import com.example.books_managment.entities.Book;

import java.util.List;

public interface AuthorService {

    public List<Author> getAllAuthors();
    public Author getAuthorById(Long id);
    public String deleteAuthor(Long id );
    Author updateAuthor(Long id, Author a);
    public Author addAuthor(Author a);

    List<Book> getBooksByAuthorId(Long authorId);
}
