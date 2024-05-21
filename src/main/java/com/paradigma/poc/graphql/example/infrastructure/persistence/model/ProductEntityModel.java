package com.paradigma.poc.graphql.example.infrastructure.persistence.model;

import java.time.OffsetDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class ProductEntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Integer price;
    private String email;
    private OffsetDateTime expeditionDate;
    private OffsetDateTime expirationDate;
    private ColorEnumModel color;

    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @OrderBy("id")
    private List<ReviewEntityModel> reviews;
}
