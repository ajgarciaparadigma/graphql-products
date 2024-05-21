package com.paradigma.poc.graphql.example.application.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.paradigma.poc.graphql.example.application.ports.in.ProductGetProductsUseCase;
import com.paradigma.poc.graphql.example.application.ports.out.ProductFindAllPort;
import com.paradigma.poc.graphql.example.domain.model.entities.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductFindAllService implements ProductGetProductsUseCase {

    private final ProductFindAllPort productRepository;

    @Override
    public Page<Product> getProducts(Integer pageNumber, Integer pageSize) {

        return productRepository.findAll(pageNumber, pageSize);
    }
}
