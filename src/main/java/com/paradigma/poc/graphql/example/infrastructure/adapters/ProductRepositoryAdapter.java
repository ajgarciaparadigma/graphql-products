package com.paradigma.poc.graphql.example.infrastructure.adapters;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paradigma.poc.graphql.example.application.ports.out.ProductDeletePort;
import com.paradigma.poc.graphql.example.application.ports.out.ProductFindAllPort;
import com.paradigma.poc.graphql.example.application.ports.out.ProductGetByIdPort;
import com.paradigma.poc.graphql.example.application.ports.out.ProductSavePort;
import com.paradigma.poc.graphql.example.domain.exceptions.ProductNotFoundException;
import com.paradigma.poc.graphql.example.domain.model.entities.Product;
import com.paradigma.poc.graphql.example.infrastructure.persistence.ProductJpaRepository;
import com.paradigma.poc.graphql.example.infrastructure.persistence.mappers.ProductMapper;
import com.paradigma.poc.graphql.example.infrastructure.persistence.model.ProductEntityModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductRepositoryAdapter
        implements ProductSavePort, ProductFindAllPort, ProductGetByIdPort, ProductDeletePort {

    private final ProductJpaRepository repository;
    private final ProductMapper mapper;

    @Override
    public Optional<Product> findById(Long id) {

        final Optional<ProductEntityModel> productMo = repository.findById(id);

        return mapper.fromOptionalModel(productMo);
    }

    @Override
    public Page<Product> findAll(Integer pageNumber, Integer pageSize) {

        final Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
        final Page<ProductEntityModel> productsMOs = repository.findAll(pageRequest);
        return mapper.fromModels(productsMOs);
    }

    @Override
    public Product save(Product product) {

        final ProductEntityModel productModel = mapper.toModel(product);
        if (productModel.getReviews() != null && productModel.getReviews().size() > 0) {
            productModel.getReviews().stream().forEach(x -> x.setProduct(productModel));
        }
        final ProductEntityModel productSaved = repository.save(productModel);

        return mapper.fromModel(productSaved);
    }

    @Override
    public void deleteById(Long id) {

        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ProductNotFoundException(String.valueOf(id));
        }
    }
}
