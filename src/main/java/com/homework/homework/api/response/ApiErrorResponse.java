package com.homework.homework.api.response;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ApiErrorResponse implements ApiResponseInterface{
    public Timestamp timestamp;
    public Integer status;
    public String error;
    public String message;
    public String path;

    public ApiErrorResponse(HttpStatus status, String message, String path)
    {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.path = path;
    }
}
