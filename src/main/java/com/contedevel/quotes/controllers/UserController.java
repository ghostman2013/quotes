package com.contedevel.quotes.controllers;

import com.contedevel.quotes.exceptions.UserNotFoundException;
import com.contedevel.quotes.models.database.entities.User;
import com.contedevel.quotes.models.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/{id}")
    public User get(@PathVariable long id) throws UserNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable long id, @RequestBody User user) {
        user.setId(id);

        return repository.save(user);
    }
}
