package com.paradigma.poc.graphql.example.domain.exceptions;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatusCode;

import java.net.URI;

@ToString(callSuper = true)
@Getter
public abstract class PDException extends RuntimeException {

    private static final long serialVersionUID = -687991492884005033L;

    private URI type;
    private String title;
    private HttpStatusCode status;
    private String detail;

    public PDException(
            @Nullable final URI type,
            @Nullable final String title,
            @Nullable final HttpStatusCode status,
            @Nullable final String detail) {

        super(title);
        this.title = title;
        this.type = type;
        this.status = status;
        this.detail = detail;
    }
}
