package com.contedevel.quotes.services;

public interface SecurityService {
    String findLoggedInEmail();
    void signInAuto(String email, String password);
}
