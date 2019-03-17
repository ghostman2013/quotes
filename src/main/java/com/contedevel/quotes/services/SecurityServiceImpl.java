package com.contedevel.quotes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String findLoggedInEmail() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();

        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }

        return null;
    }

    @Override
    public void signInAuto(String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities());

        authManager.authenticate(authToken);

        if (authToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }
}
