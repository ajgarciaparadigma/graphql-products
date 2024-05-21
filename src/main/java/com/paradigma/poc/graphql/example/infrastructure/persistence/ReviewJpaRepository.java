package com.paradigma.poc.graphql.example.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paradigma.poc.graphql.example.infrastructure.persistence.model.ReviewEntityModel;

public interface ReviewJpaRepository extends JpaRepository<ReviewEntityModel, Long> {}
