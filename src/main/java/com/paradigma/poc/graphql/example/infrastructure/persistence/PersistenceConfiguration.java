package com.paradigma.poc.graphql.example.infrastructure.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.paradigma.poc.graphql.example.infrastructure.persistence")
@EntityScan("com.paradigma.poc.graphql.example.infrastructure.persistence.model")
public class PersistenceConfiguration {}
