package com.contedevel.quotes.components.security;

import com.contedevel.quotes.models.ErrorDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public abstract class ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessDeniedHandlerImpl.class);

    private ObjectMapper objectMapper;

    protected ErrorHandler() {
        objectMapper = new ObjectMapper();
    }

    protected void handle(HttpServletRequest request, HttpServletResponse response,
                       Exception ex) throws IOException, ServletException {
        try {
            LOGGER.error(String.format("Authentication error for %s: %s",
                    request.getRequestURL(), ex.getMessage()), ex);
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED,
                    ex.getMessage(), request.getQueryString());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(objectMapper.writeValueAsString(errorDetails));
        } catch (IOException e) {
            LOGGER.error("Unexpected exception.", e);
            throw e;
        }
    }
}
