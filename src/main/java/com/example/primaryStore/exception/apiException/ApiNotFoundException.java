package com.example.primaryStore.exception.apiException;

public class ApiNotFoundException extends RuntimeException {
    public ApiNotFoundException(String message) {
        super(message);
    }

    public String ApiFoundException(String message) {
        return message;
    }

}
