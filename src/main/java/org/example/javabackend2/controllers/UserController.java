package org.example.javabackend2.controllers;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.dtos.UserDetailedDto;
import org.example.javabackend2.models.User;
import org.example.javabackend2.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(Model model, User user) {
        UserDetailedDto userLoggingIn = userService.findUserDetailedDtoByEmail(user.getEmail());

        if (userLoggingIn == null) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }

        String role = userLoggingIn.getRole().getType().toLowerCase();

        return switch (role) {
            case "admin" -> "redirect:/orders";
            case "user" -> "redirect:/products";
            default -> {
                model.addAttribute("error", "Invalid role");
                yield "login";
            }
        };
    }
}
