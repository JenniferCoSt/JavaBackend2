package org.example.javabackend2.webbservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.webbservice.dtos.UserDetailedDto;
import org.example.javabackend2.webbservice.models.User;
import org.example.javabackend2.webbservice.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

}
