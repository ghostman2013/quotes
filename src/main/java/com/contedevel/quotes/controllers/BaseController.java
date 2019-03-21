package com.contedevel.quotes.controllers;

import com.contedevel.quotes.exceptions.AuthenticationException;
import com.contedevel.quotes.exceptions.UserNotFoundException;
import com.contedevel.quotes.models.database.entities.User;
import com.contedevel.quotes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {

    @Autowired
    protected UserService userService;

    protected User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal == null) {
            throw new AuthenticationException();
        }

        long id = (Long)principal;

        return userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
