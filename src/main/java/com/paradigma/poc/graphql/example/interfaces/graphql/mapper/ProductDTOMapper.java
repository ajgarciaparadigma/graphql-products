package com.paradigma.poc.graphql.example.interfaces.graphql.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.paradigma.poc.graphql.example.domain.model.entities.Product;
import com.paradigma.poc.graphql.example.domain.model.entities.Review;
import com.paradigma.poc.graphql.example.interfaces.graphql.*;

@Mapper(builder = @Builder(disableBuilder = true))
public abstract class ProductDTOMapper {

    private static final ProductDTOMapper INSTANCE = Mappers.getMapper(ProductDTOMapper.class);

    @Mapping(target = "id", ignore = true)
    public abstract Product from(ProductRequest productRequest);

    @Mapping(target = "id", ignore = true)
    public abstract Review from(ReviewRequest productRequest);

    public abstract Product from(ProductUpdateRequest productRequest);

    public abstract ProductResponse to(Product product);

    public abstract ReviewResponse to(Review product);

    public List<ProductResponse> mapProductPageToProductsResponse(Page<Product> products) {

        return products.getContent().stream().map(INSTANCE::to).collect(Collectors.toList());
    }
}
