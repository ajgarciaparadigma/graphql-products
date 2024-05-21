package com.paradigma.poc.graphql.example.application.ports.in;

import org.springframework.data.domain.Page;

import com.paradigma.poc.graphql.example.domain.model.entities.Product;

public interface ProductGetProductsUseCase {

    Page<Product> getProducts(Integer pageNumber, Integer pageSize);
}
