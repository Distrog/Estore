package ru.stroganov.exception;

public class NotFoundPurchaseException extends RuntimeException{
    public NotFoundPurchaseException(String message) {
        super(message);
    }
}
