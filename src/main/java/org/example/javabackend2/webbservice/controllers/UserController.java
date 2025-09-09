package org.example.javabackend2.webbservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.javabackend2.webbservice.dtos.UserRegisterDto;
import org.example.javabackend2.webbservice.models.Product;
import org.example.javabackend2.webbservice.models.User;
import org.example.javabackend2.webbservice.services.ProductService;
import org.example.javabackend2.webbservice.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        List<Product> featuredProducts = productService.getFeaturedProducts();
        model.addAttribute("featuredProducts", featuredProducts);
        return "home";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new UserRegisterDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserRegisterDto userRegisterDto,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        boolean registrationSuccessful = userService.saveUser(userRegisterDto);
        if (!registrationSuccessful) {
            model.addAttribute("user", userRegisterDto);
            model.addAttribute("error", "Välj en annan e-postadress");
            return "register";
        }
        return "redirect:/login";
    }

}
