package com.contedevel.quotes.exceptions;

public class QuoteNotFoundException extends RuntimeException {

    public QuoteNotFoundException(long id) {
        super(String.format("Quote with ID %d not found.", id));
    }
}
