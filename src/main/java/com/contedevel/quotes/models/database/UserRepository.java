package com.contedevel.quotes.models.database;

import com.contedevel.quotes.models.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
