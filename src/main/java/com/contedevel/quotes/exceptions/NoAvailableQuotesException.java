package com.contedevel.quotes.exceptions;

public class NoAvailableQuotesException extends RuntimeException {

    public NoAvailableQuotesException() {
        super("No available quotes.");
    }
}
