package com.sabulous.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductNotFoundException extends RuntimeException {

    @ExceptionHandler (value = ProductNotFoundException.class)
    public ResponseEntity<Object> exception(ProductNotFoundException exception) {
        return new ResponseEntity<Object>("Error : Product not found.", HttpStatus.NOT_FOUND);
        //TODO fetch exception strings from application.properties
    }

}

