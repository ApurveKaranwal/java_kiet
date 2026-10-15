package com.kiet.library.exception;

/**
 * Exception thrown when a requested resource (e.g. Book) is not found in the database.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
