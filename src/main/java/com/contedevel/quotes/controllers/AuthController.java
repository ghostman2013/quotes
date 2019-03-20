package com.contedevel.quotes.controllers;

import com.contedevel.quotes.exceptions.AuthenticationException;
import com.contedevel.quotes.models.database.entities.User;
import com.contedevel.quotes.security.HeaderUtils;
import com.contedevel.quotes.security.UserCredentials;
import com.contedevel.quotes.services.JwtService;
import com.contedevel.quotes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("token")
    public String token(@RequestHeader("Authorization") String header) {
        UserCredentials creds = HeaderUtils.parseAuthorizationHeader(header);
        User user = userService.findByEmail(creds.getEmail())
                .orElseThrow(AuthenticationException::new);

        if (!userService.matchPassword(creds.getPassword(), user.getPassword())) {
            throw new AuthenticationException();
        }

        return jwtService.generateToken(user);
    }
}
