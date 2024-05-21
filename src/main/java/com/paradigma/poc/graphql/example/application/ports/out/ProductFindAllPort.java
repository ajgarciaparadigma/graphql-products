package com.paradigma.poc.graphql.example.application.ports.out;

import org.springframework.data.domain.Page;

import com.paradigma.poc.graphql.example.domain.model.entities.Product;

public interface ProductFindAllPort {
    Page<Product> findAll(Integer pageNumber, Integer pageSize);
}
