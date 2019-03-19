package com.contedevel.quotes.controllers;

import com.contedevel.quotes.exceptions.NoAvailableQuotesException;
import com.contedevel.quotes.exceptions.QuoteNotFoundException;
import com.contedevel.quotes.exceptions.UserNotFoundException;
import com.contedevel.quotes.models.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

@RestControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(RestErrorHandler.class);

    @ExceptionHandler(value = {
            QuoteNotFoundException.class,
            UserNotFoundException.class,
            NoAvailableQuotesException.class
    })
    public ResponseEntity<ErrorDetails> handleEntityNotFoundExceptions(
            RuntimeException ex, WebRequest request) {
        logger.error(String.format("Entity not found: %s", ex.getMessage()), ex);
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND, ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        StringBuilder sb = new StringBuilder();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            sb.append(error.getField()).append(" : ").append(error.getDefaultMessage());
        }

        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            sb.append(error.getObjectName()).append(" : ").append(error.getDefaultMessage());
        }

        logger.error(String.format("Invalid method argument: %s", ex.getMessage()), ex);
        ErrorDetails errorDetails = new ErrorDetails(status, ex.getMessage(), sb.toString());
        return new ResponseEntity<>(errorDetails, status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        logger.error(String.format("Internal exception: %s", ex.getMessage()), ex);
        ErrorDetails errorDetails = new ErrorDetails(status, ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllExceptions(
            Exception ex, HttpStatus status, WebRequest request) {
        logger.error(String.format("Exception: %s", ex.getMessage()), ex);
        ErrorDetails errorDetails = new ErrorDetails(status, ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, status);
    }
}
