package com.homework.homework.commandbus.exception;

public class CommandExecutionFailedException extends Exception {
    public CommandExecutionFailedException(String errorMessage) {
        super(errorMessage);
    }
}
