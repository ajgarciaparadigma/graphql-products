package com.paradigma.poc.graphql.example.application.ports.in;

import com.paradigma.poc.graphql.example.domain.model.entities.Product;

public interface ProductGetByIdUseCase {

    Product getProduct(Long id);
}
