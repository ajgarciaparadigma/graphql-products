package com.paradigma.poc.graphql.example.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import com.paradigma.poc.graphql.example.infrastructure.persistence.model.ProductEntityModel;

@GraphQlRepository
public interface ProductJpaRepository extends JpaRepository<ProductEntityModel, Long> {}
