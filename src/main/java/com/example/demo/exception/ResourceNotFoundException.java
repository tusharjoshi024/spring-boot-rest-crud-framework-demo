package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException {

    public ResourceNotFoundException(String type, Long id) {
        super(HttpStatus.NOT_FOUND,
                "Resource of type "+type+" with id '"+id+"' was not found in the data source");
    }

    public ResourceNotFoundException(String type, Long id, Throwable cause) {
        super(HttpStatus.NOT_FOUND,
                "Resource of type "+type+" with id '"+id+"' was not found in the data source", cause);
    }
}
