package com.contedevel.quotes.services;

import com.contedevel.quotes.models.database.entities.User;

public interface UserService {
    void save(User user);
    User findByEmail(String email);
}
