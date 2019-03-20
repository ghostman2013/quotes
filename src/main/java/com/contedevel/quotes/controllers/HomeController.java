package com.contedevel.quotes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/vue/register.vue")
    public String register() {

        return "vue/register.vue";
    }

    @GetMapping("/vue/login.vue")
    public String login() {
        return "vue/login.vue";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
