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
public class AuthorServiceImp implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        return optionalAuthor.orElseThrow(() -> new NotFoundException("Author not found with id: " + id));
    }

    @Override
    public String deleteAuthor(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            List<Book> books = author.getBooks();
            if (!books.isEmpty()) {
                throw new NotFoundException("Cannot delete author as they have associated books.");
            } else {
                authorRepository.deleteById(id);
                return "Author deleted successfully.";
            }
        } else {
            throw new NotFoundException("Author not found with id: " + id);
        }
    }

    @Override
    public Author updateAuthor(Long id, Author updatedAuthor) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author existingAuthor = optionalAuthor.get();
            existingAuthor.setFirstName(updatedAuthor.getFirstName());
            existingAuthor.setLastName(updatedAuthor.getLastName());
            return authorRepository.save(existingAuthor);
        } else {
            return null;
        }
    }

    @Override
    public Author addAuthor(Author newAuthor) {
        return authorRepository.save(newAuthor);
    }

    @Override
    public List<Book> getBooksByAuthorId(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author not found with id: " + authorId));
        return author.getBooks();
    }
}