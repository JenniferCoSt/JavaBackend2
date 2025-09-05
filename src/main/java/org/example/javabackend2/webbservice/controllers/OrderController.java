package org.example.javabackend2.webbservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.webbservice.dtos.OrderDto;
import org.example.javabackend2.webbservice.dtos.ProductDto;
import org.example.javabackend2.webbservice.models.Order;
import org.example.javabackend2.webbservice.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders/confirmed")
    public String addOrder(Model model, @RequestParam Long id) {

        OrderDto newOrder=orderService.createOrderFromProdId(id);
        String status = "SUCCESS";

        if (newOrder.getId() == null) {
            status = "NOT_CREATED";
        }
        if (newOrder.getUser() == null) {
            status = "NO_USER";
        }
        if(newOrder.getProduct() == null) {
            status = "NO_PRODUCT";
        }

        model.addAttribute("status", status);
        model.addAttribute("order", newOrder);


        return "purchaseConfirmation";
    }


    @GetMapping({ "/orders"})
    public String listProducts(Model model) {

        List<OrderDto> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }

}
