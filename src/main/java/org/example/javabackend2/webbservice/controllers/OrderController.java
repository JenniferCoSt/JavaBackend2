package org.example.javabackend2.webbservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class OrderController {

    @GetMapping("/orders")
    public String showOrders(Model model) {
        return "orders";
    }
}
