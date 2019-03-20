package com.contedevel.quotes.controllers;

import com.contedevel.quotes.exceptions.UserExistsException;
import com.contedevel.quotes.exceptions.UserNotFoundException;
import com.contedevel.quotes.models.database.entities.User;
import com.contedevel.quotes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User get(@PathVariable long id) throws UserNotFoundException {
        return userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) {

        if (userService.existsByEmail(user.getEmail())) {
            throw new UserExistsException();
        }

        return userService.save(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@PathVariable long id, @Valid @RequestBody User user) {
        user.setId(id);

        return userService.save(user);
    }
}
