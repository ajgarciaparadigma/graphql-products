package com.paradigma.poc.graphql.example.domain.model.enums;

import lombok.Getter;

@Getter
public enum ColorEnum {
    BLACK("black"),
    WHITE("white"),
    RED("red"),
    GREEN("green"),
    BLUE("blue");

    private final String value;

    ColorEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
