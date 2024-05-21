package com.paradigma.poc.graphql.example.application.ports.out;

import java.util.Optional;

import com.paradigma.poc.graphql.example.domain.model.entities.Product;

public interface ProductGetByIdPort {

    Optional<Product> findById(Long id);
}
