package ru.stroganov.exception;

public class NotFoundPurchaseTypeException extends RuntimeException{
    public NotFoundPurchaseTypeException(String message) {
        super(message);
    }
}
