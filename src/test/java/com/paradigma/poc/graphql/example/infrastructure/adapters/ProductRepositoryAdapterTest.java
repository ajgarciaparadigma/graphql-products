package com.paradigma.poc.graphql.example.infrastructure.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;

import java.util.Collections;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.paradigma.poc.graphql.example.domain.exceptions.ProductNotFoundException;
import com.paradigma.poc.graphql.example.domain.model.entities.Product;
import com.paradigma.poc.graphql.example.infrastructure.persistence.ProductJpaRepository;
import com.paradigma.poc.graphql.example.infrastructure.persistence.mappers.ProductMapper;
import com.paradigma.poc.graphql.example.infrastructure.persistence.model.ProductEntityModel;

import lombok.SneakyThrows;

@ExtendWith(SpringExtension.class)
class ProductRepositoryAdapterTest {

    private static final long TEST_ID = 123;

    @Mock private ProductJpaRepository productMORepositoryMock;

    @Mock private ProductMapper productMapperMock;

    private ProductRepositoryAdapter productCrudRepository;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        productCrudRepository =
                new ProductRepositoryAdapter(productMORepositoryMock, productMapperMock);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should get a product by id")
    void shouldGetProduct() {

        final ProductEntityModel testProductEntityModel = new ProductEntityModel();
        final Optional<Product> testProduct = Optional.of(new Product());

        given(productMORepositoryMock.findById(anyLong()))
                .willReturn(Optional.of(testProductEntityModel));

        given(productMapperMock.fromOptionalModel(any(Optional.class))).willReturn(testProduct);

        final Optional<Product> productRetrieved = productCrudRepository.findById(TEST_ID);

        assertEquals(productRetrieved, testProduct);

        then(productMORepositoryMock).should().findById(TEST_ID);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should get a page from all product")
    void shouldGetProducts() {

        final Page<ProductEntityModel> testPageModel = new PageImpl<>(Collections.emptyList());
        final Page<Product> testPage = new PageImpl<>(Collections.emptyList());

        given(productMORepositoryMock.findAll(PageRequest.of(1, 10))).willReturn(testPageModel);
        given(productMapperMock.fromModels(any(Page.class))).willReturn(testPage);

        final Page<Product> productsPage = productCrudRepository.findAll(1, 10);

        assertEquals(productsPage, testPage);

        then(productMORepositoryMock).should().findAll(any(PageRequest.class));
    }

    @Test
    @SneakyThrows
    @DisplayName("Should get a page from all product by name")
    void shouldGetProductsByName() {

        final Page<ProductEntityModel> testPageModel = new PageImpl<>(Collections.emptyList());
        final Page<Product> testPage = new PageImpl<>(Collections.emptyList());

        given(productMORepositoryMock.findAll(any(PageRequest.class))).willReturn(testPageModel);

        given(productMapperMock.fromModels(any(Page.class))).willReturn(testPage);

        final Page<Product> productsPage = productCrudRepository.findAll(1, 10);

        assertEquals(productsPage, testPage);

        then(productMORepositoryMock).should().findAll(any(PageRequest.class));
    }

    @Test
    @SneakyThrows
    @DisplayName("Should create a product")
    void shouldCreateProduct() {

        final Product testProduct = new Product();
        final ProductEntityModel testProductEntityModel = new ProductEntityModel();

        given(productMapperMock.toModel(any(Product.class))).willReturn(testProductEntityModel);

        given(productMORepositoryMock.save(testProductEntityModel))
                .willReturn(testProductEntityModel);

        given(productMapperMock.fromModel(any(ProductEntityModel.class))).willReturn(testProduct);

        final Product productRetrieved = productCrudRepository.save(testProduct);

        assertEquals(productRetrieved, testProduct);

        then(productMORepositoryMock).should().save(testProductEntityModel);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should delete a product")
    void shouldDeleteProduct() {

        productCrudRepository.deleteById(TEST_ID);

        then(productMORepositoryMock).should().deleteById(TEST_ID);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should not delete a product not found")
    void shouldNotDeleteProductNotFound() {

        doThrow(EmptyResultDataAccessException.class)
                .when(productMORepositoryMock)
                .deleteById(TEST_ID);

        final Executable execution = () -> productCrudRepository.deleteById(TEST_ID);

        assertThrows(ProductNotFoundException.class, execution);
    }
}
