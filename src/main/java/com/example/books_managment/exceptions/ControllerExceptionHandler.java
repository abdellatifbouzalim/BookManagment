package com.example.books_managment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(CannotDeleteAuthorException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String handleCannotDeleteAuthor(CannotDeleteAuthorException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(ValidationException ex) {
        return ex.getMessage();
    }


}
