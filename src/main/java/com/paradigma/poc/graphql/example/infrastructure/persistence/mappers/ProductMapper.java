package com.paradigma.poc.graphql.example.infrastructure.persistence.mappers;

import java.util.Optional;

import org.springframework.data.domain.Page;

import org.mapstruct.Mapper;

import com.paradigma.poc.graphql.example.domain.model.entities.Product;
import com.paradigma.poc.graphql.example.infrastructure.persistence.model.ProductEntityModel;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product fromModel(ProductEntityModel productEntityModel);

    ProductEntityModel toModel(Product product);

    default Page<Product> fromModels(Page<ProductEntityModel> productPage) {

        final Page<Product> productsPage = productPage.map(this::fromModel);

        return productsPage;
    }

    default Optional<Product> fromOptionalModel(
            Optional<ProductEntityModel> optionalProductEntity) {
        return (optionalProductEntity.isEmpty())
                ? Optional.empty()
                : Optional.of(fromModel(optionalProductEntity.get()));
    }
}
