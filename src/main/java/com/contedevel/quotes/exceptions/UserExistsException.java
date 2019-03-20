package com.contedevel.quotes.exceptions;

public class UserExistsException extends RuntimeException {

    public UserExistsException() {
        super("Email is registered already.");
    }
}
