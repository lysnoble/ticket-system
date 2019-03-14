package org.alyssanoble.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

    @Controller
    public class MainController {

        // Login form
        @RequestMapping("/login.html")
        public String login() {
            return "login.html";
        }

        // Login form with error
        @RequestMapping("/login-error.html")
        public String loginError(Model model) {
            model.addAttribute("loginError", true);
            return "login.html";
        }

    }