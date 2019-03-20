package com.contedevel.quotes.services;

import com.contedevel.quotes.models.database.entities.User;

import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findById(long id);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean matchPassword(String rawPassword, String encodedPassword);
}
