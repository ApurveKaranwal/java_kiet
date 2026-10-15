package com.kiet.library.exception;

/**
 * Exception thrown when creating or updating a book with an ISBN that already exists.
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}
