package com.homework.homework.validation.exception;

public class ValidationFailedException extends Exception {
    public ValidationFailedException(String errorMessage) {
        super(errorMessage);
    }
}
