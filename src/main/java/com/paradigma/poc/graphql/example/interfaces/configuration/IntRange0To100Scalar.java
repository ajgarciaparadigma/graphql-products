package com.paradigma.poc.graphql.example.interfaces.configuration;

import static graphql.Scalars.GraphQLInt;

import java.util.function.Function;

import graphql.Internal;
import graphql.schema.*;

@Internal
public final class IntRange0To100Scalar implements Coercing<Integer, Integer> {
    private IntRange0To100Scalar() {}

    public static final GraphQLScalarType INSTANCE =
            GraphQLScalarType.newScalar()
                    .name("IntRange0To100Scalar")
                    .description("An Int scalar that must be a positive value and max value is 100")
                    .coercing(
                            new Coercing() {
                                protected Integer check(
                                        Integer value,
                                        Function<String, RuntimeException> exceptionMaker) {
                                    if (value <= 0 || value > 100) {
                                        throw exceptionMaker.apply(
                                                "The value must be a positive integer and max value"
                                                        + " is 100");
                                    }
                                    return value;
                                }

                                @Override
                                public Integer serialize(Object input)
                                        throws CoercingSerializeException {
                                    Integer i = (Integer) GraphQLInt.getCoercing().serialize(input);
                                    return check(i, CoercingSerializeException::new);
                                }

                                @Override
                                public Integer parseValue(Object input)
                                        throws CoercingParseValueException {
                                    Integer i =
                                            (Integer) GraphQLInt.getCoercing().parseValue(input);
                                    return check(i, CoercingParseValueException::new);
                                }

                                @Override
                                public Integer parseLiteral(Object input)
                                        throws CoercingParseLiteralException {
                                    Integer i =
                                            (Integer) GraphQLInt.getCoercing().parseLiteral(input);
                                    return check(i, CoercingParseLiteralException::new);
                                }
                            })
                    .build();
}
