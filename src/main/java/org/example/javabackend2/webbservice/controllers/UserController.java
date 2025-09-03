package org.example.javabackend2.webbservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.webbservice.dtos.UserDetailedDto;
import org.example.javabackend2.webbservice.dtos.UserRegisterDto;
import org.example.javabackend2.webbservice.models.Role;
import org.example.javabackend2.webbservice.models.User;
import org.example.javabackend2.webbservice.repos.RoleRepository;
import org.example.javabackend2.webbservice.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;

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

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRegisterDto userRegisterDto,
                               Model model) {
        boolean registrationSuccessful = userService.saveUser(userRegisterDto);
        if (!registrationSuccessful) {
            model.addAttribute("user", userRegisterDto);
            model.addAttribute("error", "Choose a different email");
            return "register";
        }
        return "redirect:/login";
    }
}
