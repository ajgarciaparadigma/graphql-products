package com.paradigma.poc.graphql.example.domain.model.entities;

import java.time.OffsetDateTime;
import java.util.List;

import com.paradigma.poc.graphql.example.domain.model.enums.ColorEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String email;
    private OffsetDateTime expeditionDate;
    private OffsetDateTime expirationDate;
    private ColorEnum color;
    private List<Review> reviews;
}
