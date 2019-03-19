package com.contedevel.quotes.models;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorDetails implements IType {

    private Date timestamp;
    private String status;
    private String message;
    private String details;

    public ErrorDetails(HttpStatus status, String msg, String details) {
        this.timestamp = new Date();
        this.status = status.getReasonPhrase();
        this.message = msg;
        this.details = details;
    }

    @Override
    public String getType() {
        return "error";
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
