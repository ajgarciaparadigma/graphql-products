package com.paradigma.poc.graphql.example.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paradigma.poc.graphql.example.infrastructure.persistence.model.ProductEntityModel;

@Repository
public interface TestProductJpaRepository extends JpaRepository<ProductEntityModel, Long> {}
