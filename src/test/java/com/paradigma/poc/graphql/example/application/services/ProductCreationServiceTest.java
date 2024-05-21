package com.paradigma.poc.graphql.example.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.paradigma.poc.graphql.example.application.ports.out.ProductSavePort;
import com.paradigma.poc.graphql.example.domain.model.entities.Product;

import lombok.SneakyThrows;

@ExtendWith(SpringExtension.class)
class ProductCreationServiceTest {

    private static final long TEST_ID = 123;

    @Mock private ProductSavePort productRepositoryMock;

    private ProductCreationService productCreationService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        productCreationService = new ProductCreationService(productRepositoryMock);
    }

    @DisplayName("Should create a product")
    @SneakyThrows
    @Test
    void shouldCreateProduct() {

        Product testProduct = new Product();

        given(productRepositoryMock.save(testProduct)).willReturn(testProduct);

        Product productRetrieved = productCreationService.createProduct(testProduct);

        assertEquals(productRetrieved, testProduct);
    }
}
