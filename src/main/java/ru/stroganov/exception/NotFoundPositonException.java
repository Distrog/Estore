package ru.stroganov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundPositonException extends RuntimeException{
    public NotFoundPositonException(String message) {
        super(message);
    }
}
