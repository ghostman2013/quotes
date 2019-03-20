package com.contedevel.quotes.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(long id) {
        super(String.format("User with ID %d not found.", id));
    }

    public UserNotFoundException(String email) {
        super(String.format("User with e-mail <%s> not found.", email));
    }
}
