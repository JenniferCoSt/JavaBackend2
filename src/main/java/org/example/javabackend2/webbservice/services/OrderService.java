package org.example.javabackend2.webbservice.services;

import org.example.javabackend2.webbservice.dtos.OrderDto;
import org.example.javabackend2.webbservice.models.Order;

public interface OrderService {
    String createOrder(OrderDto order);

    void createOrderFromProdId(Long id);
}
