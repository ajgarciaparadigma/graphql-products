package com.paradigma.poc.graphql.example.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.paradigma.poc.graphql.example.application.ports.out.ProductFindAllPort;
import com.paradigma.poc.graphql.example.domain.model.entities.Product;

import lombok.SneakyThrows;

@ExtendWith(SpringExtension.class)
class ProductFindAllServiceTest {

    private static final long TEST_ID = 123;

    @Mock private ProductFindAllPort productRepositoryMock;

    private ProductFindAllService productFindAllService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        productFindAllService = new ProductFindAllService(productRepositoryMock);
    }

    @DisplayName("Should get a page from all product")
    @SneakyThrows
    @Test
    void shouldGetProducts() {

        Page<Product> testPage = new PageImpl<>(Collections.emptyList());

        given(productRepositoryMock.findAll(anyInt(), anyInt())).willReturn(testPage);

        Page<Product> productsPage = productFindAllService.getProducts(1, 10);

        assertEquals(productsPage, testPage);
    }

    @DisplayName("Should get a page from all product by name")
    @SneakyThrows
    @Test
    void shouldGetProductsByName() {

        String name = "test_name";

        Page<Product> testPage = new PageImpl<>(Collections.emptyList());

        given(productRepositoryMock.findAll(anyInt(), anyInt())).willReturn(testPage);

        Page<Product> productsPage = productFindAllService.getProducts(1, 10);

        assertEquals(productsPage, testPage);
    }
}
