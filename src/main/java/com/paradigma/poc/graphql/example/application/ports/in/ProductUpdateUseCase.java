package com.paradigma.poc.graphql.example.application.ports.in;

import com.paradigma.poc.graphql.example.domain.model.entities.Product;

public interface ProductUpdateUseCase {

    Product updateProduct(Product product);
}
