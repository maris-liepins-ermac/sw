package com.homework.homework.entitymanager.exception;

public class NoRecordsFoundException extends Exception{
    public NoRecordsFoundException(String errorMessage) {
        super(errorMessage);
    }
}
