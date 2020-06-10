package com.ilyaskrypnik.website.controller;

import com.ilyaskrypnik.website.domain.User;
import com.ilyaskrypnik.website.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {

    private final UserService userService;

    RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration.html";
    }

    @PostMapping("/register")
    public String addUser(User user, Map<String, Object> model) {
        return userService.addNewUser(user, model);
    }
}
