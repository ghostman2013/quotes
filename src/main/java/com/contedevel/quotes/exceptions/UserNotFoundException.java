package com.contedevel.quotes.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(long id) {
        super(String.format("User with ID %d not found.", id));
    }
}
