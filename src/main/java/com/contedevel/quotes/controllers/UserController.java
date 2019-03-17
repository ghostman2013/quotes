package com.contedevel.quotes.controllers;

import com.contedevel.quotes.models.User;
import com.contedevel.quotes.services.SecurityService;
import com.contedevel.quotes.services.UserService;
import com.contedevel.quotes.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/register")
    public String signUp(Model model) {
        model.addAttribute("userForm", new User());

        return "register";
    }

    @PostMapping("/register")
    public String signUp(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.save(userForm);

        securityService.signInAuto(userForm.getEmail(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String signIn(Model model, String error, String logout) {

        if (error != null) {
            model.addAttribute("error", "Email and password are invalid.");
        }

        if (logout != null)
            model.addAttribute("message", "You've been logged out successfully.");

        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
