package com.dkowalczyk.event_management.exception;

public class DuplicateEventException extends RuntimeException{
    public DuplicateEventException(String message) {
        super(message);
    }
}
