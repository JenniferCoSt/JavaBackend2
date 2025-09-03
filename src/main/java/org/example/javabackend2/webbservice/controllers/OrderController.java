package org.example.javabackend2.webbservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.webbservice.dtos.OrderDto;
import org.example.javabackend2.webbservice.models.Order;
import org.example.javabackend2.webbservice.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public String showOrders(Model model) {
        return "orders";
    }

    @PostMapping("/orders/confirmed")
    public String addOrder(Model model, @RequestParam Long id) {

        orderService.createOrderFromProdId(id);
        model.addAttribute("order", id);
        return "purchaseConfirmation";
    }


}
