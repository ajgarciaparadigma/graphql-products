package com.paradigma.poc.graphql.example.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.Optional;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.paradigma.poc.graphql.example.application.ports.out.ProductGetByIdPort;
import com.paradigma.poc.graphql.example.domain.exceptions.ProductNotFoundException;
import com.paradigma.poc.graphql.example.domain.model.entities.Product;

import lombok.SneakyThrows;

@ExtendWith(SpringExtension.class)
class ProductGetByIdlTest {

    private static final long TEST_ID = 123;

    @Mock private ProductGetByIdPort productRepositoryMock;
    private ProductGetByIdService productGetByIdService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        productGetByIdService = new ProductGetByIdService(productRepositoryMock);
    }

    @DisplayName("Should get a product by id using JDBC connector")
    @SneakyThrows
    @Test
    void shouldGetProduct() {

        Product testProduct = new Product();

        given(productRepositoryMock.findById(anyLong())).willReturn(Optional.of(testProduct));

        Product productRetrieved = productGetByIdService.getProduct(TEST_ID);

        assertEquals(productRetrieved, testProduct);

        then(productRepositoryMock).should().findById(TEST_ID);
    }

    @DisplayName("Should throw a ProductNotFound exception")
    @SneakyThrows
    @Test
    void shouldThrowProductNotFoundException() {

        given(productRepositoryMock.findById(anyLong())).willReturn(Optional.empty());

        Executable execution = () -> productGetByIdService.getProduct(TEST_ID);

        assertThrows(ProductNotFoundException.class, execution);

        then(productRepositoryMock).should().findById(TEST_ID);
    }
}
