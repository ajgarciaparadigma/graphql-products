package com.paradigma.poc.graphql.example.interfaces.graphql;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.paradigma.poc.graphql.example.application.ports.in.*;
import com.paradigma.poc.graphql.example.interfaces.graphql.mapper.ProductDTOMapper;
import com.paradigma.poc.graphql.example.interfaces.graphql.util.DataFetchersDelegateMutation;
import com.paradigma.poc.graphql.example.interfaces.graphql.util.DataFetchersDelegateProductResponse;
import com.paradigma.poc.graphql.example.interfaces.graphql.util.DataFetchersDelegateQuery;

import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController
        implements DataFetchersDelegateQuery,
                DataFetchersDelegateProductResponse,
                DataFetchersDelegateMutation {

    private final ProductGetByIdUseCase productGetByIdUseCase;
    private final ProductCreateUseCase productCreateUseCase;
    private final ProductUpdateUseCase productUpdateUseCase;
    private final ProductDeleteUseCase productDeleteUseCase;
    private final ProductGetProductsUseCase productGetProductsUseCase;
    private final ProductDTOMapper mapper;

    @Override
    public ProductResponse createProduct(
            DataFetchingEnvironment dataFetchingEnvironment, ProductRequest productRequest) {
        return mapper.to(productCreateUseCase.createProduct(mapper.from(productRequest)));
    }

    @Override
    public ProductResponse updateProduct(
            DataFetchingEnvironment dataFetchingEnvironment, ProductUpdateRequest productUpdate) {
        return mapper.to(productUpdateUseCase.updateProduct(mapper.from(productUpdate)));
    }

    @Override
    public Long deleteProduct(DataFetchingEnvironment dataFetchingEnvironment, Long id) {
        return productDeleteUseCase.deleteProduct(id);
    }

    @Override
    public List<ReviewResponse> reviews(
            DataFetchingEnvironment dataFetchingEnvironment, ProductResponse origin) {
        return origin.getReviews();
    }

    @Override
    public String firstQuery(DataFetchingEnvironment dataFetchingEnvironment) {
        return "hola";
    }

    @Override
    public ProductResponse product(DataFetchingEnvironment dataFetchingEnvironment, Long id) {
        return mapper.to(productGetByIdUseCase.getProduct(id));
    }

    @Override
    public List<ProductResponse> productPaged(
            DataFetchingEnvironment dataFetchingEnvironment, Integer pageNumber, Integer pageSize) {
        return mapper.mapProductPageToProductsResponse(
                productGetProductsUseCase.getProducts(pageNumber, pageSize));
    }
}
