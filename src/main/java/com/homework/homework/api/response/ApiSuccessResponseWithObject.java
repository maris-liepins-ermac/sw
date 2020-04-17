package com.homework.homework.api.response;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ApiSuccessResponseWithObject<T> implements ApiResponseInterface{
    public Timestamp timestamp;
    public Integer status;
    public String message;
    public String path;
    public T object;

    public ApiSuccessResponseWithObject(HttpStatus status, String path, T object)
    {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.status = status.value();
        this.message = status.getReasonPhrase();
        this.path = path;
        this.object = object;
    }
}
