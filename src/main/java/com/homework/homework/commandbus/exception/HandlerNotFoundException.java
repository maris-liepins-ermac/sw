package com.homework.homework.commandbus.exception;

public class HandlerNotFoundException extends Exception {
    public HandlerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
