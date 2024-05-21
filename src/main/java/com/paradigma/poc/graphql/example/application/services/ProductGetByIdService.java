package com.paradigma.poc.graphql.example.application.services;

import org.springframework.stereotype.Service;

import com.paradigma.poc.graphql.example.application.ports.in.ProductGetByIdUseCase;
import com.paradigma.poc.graphql.example.application.ports.out.ProductGetByIdPort;
import com.paradigma.poc.graphql.example.domain.exceptions.ProductNotFoundException;
import com.paradigma.poc.graphql.example.domain.model.entities.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductGetByIdService implements ProductGetByIdUseCase {

    private final ProductGetByIdPort productRepository;

    @Override
    public Product getProduct(Long id) {

        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.valueOf(id)));
    }
}
