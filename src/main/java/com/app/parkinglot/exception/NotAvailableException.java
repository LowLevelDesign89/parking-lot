package com.app.parkinglot.exception;

public class NotAvailableException extends RuntimeException{
    public NotAvailableException(String message) {
        super(message);
    }
}
