package com.contedevel.quotes.services;

import com.contedevel.quotes.models.database.entities.User;

import java.util.Optional;

public interface UserService {
    void save(User user);
    Optional<User> findByEmail(String email);
    boolean matchPassword(String rawPassword, String encodedPassword);
}
