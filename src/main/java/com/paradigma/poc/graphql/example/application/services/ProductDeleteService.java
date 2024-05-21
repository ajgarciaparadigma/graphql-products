package com.paradigma.poc.graphql.example.application.services;

import org.springframework.stereotype.Service;

import com.paradigma.poc.graphql.example.application.ports.in.ProductDeleteUseCase;
import com.paradigma.poc.graphql.example.application.ports.out.ProductDeletePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductDeleteService implements ProductDeleteUseCase {

    private final ProductDeletePort productRepository;

    @Override
    public Long deleteProduct(Long id) {

        productRepository.deleteById(id);
        return id;
    }
}
