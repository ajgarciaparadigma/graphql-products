package com.paradigma.poc.graphql.example.infrastructure.persistence.model;

import lombok.Getter;

@Getter
public enum ColorEnumModel {
    BLACK("black"),
    WHITE("white"),
    RED("red"),
    GREEN("green"),
    BLUE("blue");

    private final String value;

    ColorEnumModel(String value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
