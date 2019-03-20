package com.contedevel.quotes.services;

import com.contedevel.quotes.models.database.entities.User;

public interface JwtService {
    String generateToken(User user);
}
