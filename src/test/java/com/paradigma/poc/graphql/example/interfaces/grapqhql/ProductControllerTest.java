package com.paradigma.poc.graphql.example.interfaces.grapqhql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.server.WebGraphQlHandler;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.WebGraphQlTester;
import org.springframework.test.context.jdbc.Sql;

import org.junit.jupiter.api.Test;

import com.paradigma.poc.graphql.example.interfaces.graphql.ColorEnum;
import com.paradigma.poc.graphql.example.interfaces.graphql.ProductResponse;
import com.paradigma.poc.graphql.example.interfaces.graphql.util.*;

@SpringBootTest(classes = {GraphQLServerMain.class})
public class ProductControllerTest {

    @Autowired private GraphQlTester graphQlTester;

    @Test
    @Sql(value = {"/sql/test_truncate_products.sql", "/sql/test_products.sql"})
    void shouldGetProductById() {
        final String document =
                """
                  query{
                    product(id:1){
                      id,
                      color,
                      email,
                          reviews {
                                      id
                                      comment
                                      evaluation
                                    }
                    }
                  }
                    """;
        this.graphQlTester
                .document(document)
                .execute()
                .path("product")
                .hasValue()
                .entity(ProductResponse.class)
                .satisfies(
                        response -> {
                            assertEquals(1, response.getId());
                            assertEquals("jj@gg.com", response.getEmail());
                        });
    }

    @Test
    @Sql(value = {"/sql/test_truncate_products.sql", "/sql/test_products_update.sql"})
    void shouldMutateDelete() {
        final String document =
                """
      mutation {
        deleteProduct(id: 1)
      }
          """;
        this.graphQlTester
                .document(document)
                .execute()
                .path("deleteProduct")
                .hasValue()
                .entity(Long.class)
                .satisfies(
                        response -> {
                            assertEquals(1L, response);
                        });
    }

    @Test
    @Sql(value = {"/sql/test_truncate_products.sql", "/sql/test_products_update.sql"})
    void shouldMutateUpdateProduct() {
        final String document =
                """
                          mutation{
                            updateProduct( productUpdate:{
                              id:1
                              name: "nombre",
                              expeditionDate: "2000-10-31T01:30:00.000-05:00",
                              expirationDate:"2000-10-31T01:30:00.000-05:00",
                              email: "ajag@fff.es"
                              price: 54,
                              color: BLUE,
                              reviews:[
                                {
                                evaluation:1,
                                comment:"ff"
                                },
                                {
                                evaluation:6,
                                comment:"sec"
                              	}
                              ]
                            }) {
                              id,
                              name,
                              email,
                              price,
                              expeditionDate
                              color,
                              reviews {
                                id,
                                comment,
                                evaluation
                              }
                            }
                          }
                  """;
        this.graphQlTester
                .document(document)
                .execute()
                .path("updateProduct")
                .hasValue()
                .entity(ProductResponse.class)
                .satisfies(
                        response -> {
                            assertEquals(1, response.getId());
                            assertEquals("ajag@fff.es", response.getEmail());
                        });
    }

    @Test
    void shouldGetFirstQuery() {
        final String document =
                """
          query {
              firstQuery
          }
          """;
        this.graphQlTester
                .document(document)
                .execute()
                .path("firstQuery")
                .hasValue()
                .entity(String.class)
                .satisfies(
                        response -> {
                            assertEquals("hola", response);
                        });
    }

    @Test
    @Sql(value = {"/sql/test_truncate_products.sql"})
    void shouldMutateProductCreation() {
        final String document =
                """
      mutation{
        createProduct( productRequest:{
          name: "nombre",
          expeditionDate: "2000-10-31T01:30:00.000-05:00",
          expirationDate:"2000-10-31T01:30:00.000-05:00",
          email: "ajag@fff.es"
          price: 54,
          color: BLUE,
          reviews:[
            {
            evaluation:1,
            comment:"first comment"
            },
            {
            evaluation:6,
            comment:"second comment"
            }
          ]
        }) {
          id,
          name,
          price,
          expeditionDate
          color,
          reviews {
            id,
            comment,
            evaluation
          }
        }
      }
          """;
        this.graphQlTester
                .document(document)
                .execute()
                .path("createProduct")
                .hasValue()
                .entity(ProductResponse.class)
                .satisfies(
                        response -> {
                            assertEquals(ColorEnum.BLUE, response.getColor());
                            assertEquals("nombre", response.getName());
                            assertEquals(54, response.getPrice());
                            assertEquals(
                                    "first comment", response.getReviews().get(0).getComment());
                            assertEquals(
                                    "second comment", response.getReviews().get(1).getComment());
                        });
    }

    @Test
    @Sql(value = {"/sql/test_truncate_products.sql"})
    void shouldFailMutateProductCreation() {
        final String document =
                """
        mutation{
        createProduct( productRequest:{
        name: "nombre",
        expeditionDate: "2000-10-31T01:30:00.000-05:00",
        expirationDate:"2000-10-31T01:30:00.000-05:00",
        email: "ajagfff.es"
        price: 554,
        color: BLUE
        }) {
        id,
        name,
        price,
        expeditionDate
        color,
        reviews {
          id,
          comment,
          evaluation
        }
        }
        }
        """;
        this.graphQlTester
                .document(document)
                .execute()
                .errors()
                .satisfy(
                        errors -> {
                            assertThat(errors).isNotEmpty();
                            assertThat(errors).size().isEqualTo(1);
                            assertThat(errors.get(0).getMessage())
                                    .contains("is not a valid 'Email");
                        });
    }

    @Configuration
    static class GraphQlTestConfig {
        @Bean
        GraphQlTester graphQlTester(WebGraphQlHandler handler) {
            return WebGraphQlTester.create(handler);
        }
    }
}
