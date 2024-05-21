package com.paradigma.poc.graphql.example.domain.exceptions;

import java.net.URI;

import org.springframework.http.HttpStatus;


public class ProductNotFoundException extends PDException {
    public ProductNotFoundException(String id) {
        super(
                (URI) null,
                "Not found",
                HttpStatus.NOT_FOUND,
                String.format("Product with id %s not found", id));
    }
}
