package com.paradigma.poc.graphql.example.application.services;

import org.springframework.stereotype.Service;

import com.paradigma.poc.graphql.example.application.ports.in.*;
import com.paradigma.poc.graphql.example.application.ports.out.ProductGetByIdPort;
import com.paradigma.poc.graphql.example.application.ports.out.ProductSavePort;
import com.paradigma.poc.graphql.example.domain.exceptions.ProductNotFoundException;
import com.paradigma.poc.graphql.example.domain.model.entities.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductUpdateService implements ProductUpdateUseCase {

    private final ProductSavePort productSavePort;

    private final ProductGetByIdPort productGetByIdPort;

    @Override
    public Product updateProduct(Product product) {

        Long id = product.getId();

        if (productGetByIdPort.findById(id).isPresent()) {
            return productSavePort.save(product);
        }

        throw new ProductNotFoundException(String.valueOf(id));
    }
}
