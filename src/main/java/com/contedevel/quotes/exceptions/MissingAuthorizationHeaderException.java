package com.contedevel.quotes.exceptions;

public class MissingAuthorizationHeaderException extends RuntimeException {

    public MissingAuthorizationHeaderException() {
        super("Missing `Authorization: Basic` header.");
    }
}
