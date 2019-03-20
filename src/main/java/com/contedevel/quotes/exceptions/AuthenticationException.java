package com.contedevel.quotes.exceptions;

public class AuthenticationException extends org.springframework.security.core.AuthenticationException {

    public AuthenticationException() {
        super("Invalid email or password.");
    }
}
