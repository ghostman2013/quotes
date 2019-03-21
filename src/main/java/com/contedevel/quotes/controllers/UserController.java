package com.contedevel.quotes.controllers;

import com.contedevel.quotes.exceptions.UserExistsException;
import com.contedevel.quotes.exceptions.UserNotFoundException;
import com.contedevel.quotes.models.database.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends BaseController {

    @GetMapping("/me")
    public User get() throws UserNotFoundException {
        return getCurrentUser();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) {

        if (userService.existsByEmail(user.getEmail())) {
            throw new UserExistsException();
        }

        return userService.save(user);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@Valid @RequestBody User user) {
        User currentUser = getCurrentUser();
        user.setId(currentUser.getId());

        return userService.save(user);
    }
}
