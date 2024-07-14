package ru.stroganov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundElectroitemException extends RuntimeException{
    public NotFoundElectroitemException(String message) {
        super(message);
    }
}
