package com.paradigma.poc.graphql.example.application.ports.out;

import com.paradigma.poc.graphql.example.domain.model.entities.Product;

public interface ProductSavePort {

    Product save(Product entity);
}
