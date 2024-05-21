package com.paradigma.poc.graphql.example.application.services;

import org.springframework.stereotype.Service;

import com.paradigma.poc.graphql.example.application.ports.in.ProductCreateUseCase;
import com.paradigma.poc.graphql.example.application.ports.out.ProductSavePort;
import com.paradigma.poc.graphql.example.domain.model.entities.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCreationService implements ProductCreateUseCase {

    private final ProductSavePort productRepository;

    @Override
    public Product createProduct(Product product) {

        return productRepository.save(product);
    }
}
