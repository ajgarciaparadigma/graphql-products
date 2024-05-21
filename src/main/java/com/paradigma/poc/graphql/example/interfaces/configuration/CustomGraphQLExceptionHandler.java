package com.paradigma.poc.graphql.example.interfaces.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import com.paradigma.poc.graphql.example.domain.exceptions.*;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CustomGraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof ProductNotFoundException productNotFoundException) {
            return GraphqlErrorBuilder.newError()
                    .message(productNotFoundException.getDetail())
                    .extensions(createExtensions(ex))
                    .errorType(ErrorType.DataFetchingException)
                    .build();
        } else if (ex instanceof PDException pdException) {
            return GraphqlErrorBuilder.newError()
                    .message(ex.getMessage())
                    .extensions(createExtensions(ex))
                    .errorType(ErrorType.ExecutionAborted)
                    .build();
        } else {
            return GraphqlErrorBuilder.newError()
                    .extensions(createExtensions(ex))
                    .errorType(ErrorType.ExecutionAborted)
                    .build();
        }
    }

    private Map<String, Object> createExtensions(Throwable throwable) {
        Map<String, Object> extensions = new HashMap<>();
        extensions.put("exceptionType", throwable.getClass().getName());
        extensions.put("stackTrace", getStackTraceAsString(throwable));
        return extensions;
    }

    private String getStackTraceAsString(Throwable throwable) {
        StringBuilder result = new StringBuilder();
        for (StackTraceElement element : throwable.getStackTrace()) {
            result.append(element.toString()).append("\n");
        }
        return result.toString();
    }
}
