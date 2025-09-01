package org.example.javabackend2.webbservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.webbservice.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

}
