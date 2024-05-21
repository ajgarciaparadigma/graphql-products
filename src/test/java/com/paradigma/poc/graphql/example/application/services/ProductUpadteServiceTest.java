package com.paradigma.poc.graphql.example.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

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
import com.paradigma.poc.graphql.example.application.ports.out.ProductSavePort;
import com.paradigma.poc.graphql.example.domain.exceptions.ProductNotFoundException;
import com.paradigma.poc.graphql.example.domain.model.entities.Product;

import lombok.SneakyThrows;

@ExtendWith(SpringExtension.class)
class ProductUpadteServiceTest {

    private static final long TEST_ID = 123;

    @Mock private ProductSavePort productSavePortMock;
    @Mock private ProductGetByIdPort productGetByIdPortMock;

    private ProductUpdateService productUpdateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productUpdateService =
                new ProductUpdateService(productSavePortMock, productGetByIdPortMock);
    }

    @DisplayName("Should update a product")
    @SneakyThrows
    @Test
    void shouldUpdateProduct() {

        Product testProduct = new Product();

        testProduct.setId(TEST_ID);

        given(productGetByIdPortMock.findById(TEST_ID)).willReturn(Optional.of(testProduct));

        given(productSavePortMock.save(testProduct)).willReturn(testProduct);

        Product productRetrieved = productUpdateService.updateProduct(testProduct);

        assertEquals(productRetrieved, testProduct);

        then(productGetByIdPortMock).should().findById(TEST_ID);
    }

    @DisplayName("Should not update non-existing product")
    @SneakyThrows
    @Test
    void shouldNotUpdateNonExistingProduct() {

        Product testProduct = new Product();
        testProduct.setId(TEST_ID);

        given(productGetByIdPortMock.findById(TEST_ID)).willReturn(Optional.empty());

        Executable execution = () -> productUpdateService.updateProduct(testProduct);

        assertThrows(ProductNotFoundException.class, execution);

        then(productGetByIdPortMock).should().findById(TEST_ID);

        verify(productSavePortMock, never()).save(any(Product.class));
    }
}
