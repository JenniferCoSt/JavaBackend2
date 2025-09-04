package org.example.javabackend2.webbservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.webbservice.dtos.OrderDto;
import org.example.javabackend2.webbservice.dtos.UserDto;
import org.example.javabackend2.webbservice.mappers.OrderMapper;
import org.example.javabackend2.webbservice.models.Order;
import org.example.javabackend2.webbservice.repos.OrderRepository;
import org.example.javabackend2.webbservice.repos.ProductRepository;
import org.example.javabackend2.webbservice.services.OrderService;
import org.example.javabackend2.webbservice.services.ProductService;
import org.example.javabackend2.webbservice.services.UserService;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;


    @Override
    public String createOrder(OrderDto order) {
        Order newOrder = orderMapper.orderDtoToOrder(order);

        if (newOrder.getUser() == null) {
            return "User not found";
        }

        if (newOrder.getProduct() == null) {
            return "Product not found";
        }

        orderRepository.save(newOrder);
        return "Order created";
    }


    //TODO add user to order
    @Override
    public void createOrderFromProdId(Long id) {

        OrderDto orderDto = new OrderDto();
        orderDto.setProduct(productService.getProductById(id));
        orderDto.setUser(userService.findUserDtoByEmail("olafsdottir@4ever.se"));

        createOrder(orderDto);
    }
}
