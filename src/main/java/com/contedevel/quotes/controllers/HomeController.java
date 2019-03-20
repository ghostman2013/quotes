package com.contedevel.quotes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/vue/registration.vue")
    public String registration() {
        return "vue/registration.vue";
    }

    @GetMapping("/vue/login.vue")
    public String login() {
        return "vue/login.vue";
    }

    @GetMapping("/vue/profile.vue")
    public String profile() {
        return "vue/profile.vue";
    }

    @GetMapping("/vue/greeting.vue")
    public String greeting() {
        return "vue/greeting.vue";
    }

    @GetMapping("/vue/top10.vue")
    public String top10(ModelMap model) {
        model.addAttribute("mode", "top10");

        return "vue/quotes.vue";
    }

    @GetMapping("/vue/flop10.vue")
    public String flop10(ModelMap model) {
        model.addAttribute("mode", "flop10");

        return "vue/quotes.vue";
    }

    @GetMapping("/vue/last.vue")
    public String last(ModelMap model) {
        model.addAttribute("mode", "last");

        return "vue/quotes.vue";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
