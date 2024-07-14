package ru.stroganov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GendetFormatException extends RuntimeException{
    public GendetFormatException(String message) {
        super(message);
    }
}
