package com.contedevel.quotes.controllers;

import com.contedevel.quotes.models.database.entities.User;
import com.contedevel.quotes.services.SecurityService;
import com.contedevel.quotes.services.UserService;
import com.contedevel.quotes.components.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/vue/register.vue")
    public String register(Model model) {
        model.addAttribute("userForm", new User());

        return "vue/register.vue";
    }

    @PostMapping("/vue/register.vue")
    public String register(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "vue/register.vue";
        }

        userService.save(userForm);

        securityService.signInAuto(userForm.getEmail(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @GetMapping("/vue/login.vue")
    public String login(Model model, String error, String logout) {

        if (error != null) {
            model.addAttribute("loginError", true);
        }

        if (logout != null)
            model.addAttribute("message", "You've been logged out successfully.");

        return "vue/login.vue";
    }

    @RequestMapping("/vue/login-error.vue")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);

        return "vue/login.vue";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
