package com.example.primaryStore.exception.handler;

import com.example.primaryStore.exception.apiException.ApiNotFoundException;
import com.example.primaryStore.exception.model.ExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ApiNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiNotFoundException e) {
        HttpStatus notFound = HttpStatus.valueOf(HttpStatus.NOT_FOUND.value());
        ExceptionModel exceptionModel = new ExceptionModel(e.getMessage(), notFound);
        return new ResponseEntity<>(exceptionModel, notFound);
    }
}
