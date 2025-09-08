package org.example.javabackend2.webbservice.services;

import org.example.javabackend2.webbservice.dtos.OrderDto;
import org.example.javabackend2.webbservice.dtos.ProductDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto order);
    List<OrderDto> getAllOrders();
    OrderDto createOrderFromProdId(Long id);

    void deleteById(long id);
}
