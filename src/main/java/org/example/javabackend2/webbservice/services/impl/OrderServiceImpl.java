package org.example.javabackend2.webbservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.webbservice.dtos.OrderDto;
import org.example.javabackend2.webbservice.dtos.ProductDto;
import org.example.javabackend2.webbservice.mappers.OrderMapper;
import org.example.javabackend2.webbservice.models.Order;
import org.example.javabackend2.webbservice.repos.OrderRepository;
import org.example.javabackend2.webbservice.services.OrderService;
import org.example.javabackend2.webbservice.services.ProductService;
import org.example.javabackend2.webbservice.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;


    @Override
    public OrderDto createOrder(OrderDto order) {
        Order newOrder = orderMapper.orderDtoToOrder(order);

        Order saved = orderRepository.save(newOrder);

        return orderMapper.orderToOrderDto(saved);
    }


    @Override
    public OrderDto createOrderFromProdId(Long id) {

        OrderDto result = new OrderDto();
        result.setProduct(productService.getProductById(id));
        if (result.getProduct() == null) {
            return result;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        result.setUser(userService.findUserDtoByEmail(email));
        if (result.getUser() == null) {
            return result;
        }

        result = createOrder(result);

        return result;
    }

    @Override
    public void deleteById(long id) {
        orderRepository.deleteById(id);
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::orderToOrderDto).toList();
    }
}
