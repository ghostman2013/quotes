package com.contedevel.quotes.exceptions;

public class InvalidAuthorizationHeaderException extends RuntimeException {

    public InvalidAuthorizationHeaderException() {
        super("Invalid `Authorization` header.");
    }
}
