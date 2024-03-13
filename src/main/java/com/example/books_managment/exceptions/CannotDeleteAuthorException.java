package com.example.books_managment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CannotDeleteAuthorException extends  RuntimeException {

    public CannotDeleteAuthorException(String message) {
        super(message);
    }
}
