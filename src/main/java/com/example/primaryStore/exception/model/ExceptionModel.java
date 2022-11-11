package com.example.primaryStore.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public class ExceptionModel {
    private final String message;
    private final HttpStatus httpStatus;
}
