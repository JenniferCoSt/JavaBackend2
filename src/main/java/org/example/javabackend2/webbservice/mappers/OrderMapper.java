package org.example.javabackend2.webbservice.mappers;

import org.example.javabackend2.webbservice.dtos.OrderDto;
import org.example.javabackend2.webbservice.dtos.ProductDto;

import org.example.javabackend2.webbservice.dtos.UserDto;
import org.example.javabackend2.webbservice.models.Order;
import org.example.javabackend2.webbservice.models.Product;
import org.example.javabackend2.webbservice.models.User;
import org.example.javabackend2.webbservice.repos.ProductRepository;
import org.example.javabackend2.webbservice.repos.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {


    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderMapper(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public OrderDto orderToOrderDto(Order o) {
        Product p = o.getProduct();
        User u = o.getUser();

        return OrderDto.builder()
                .id(o.getId())
                .product(new ProductDto(
                        p.getId(),
                        p.getTitle(),
                        p.getDescription(),
                        p.getPrice(),
                        p.getImage(),
                        p.getCategory().getType(),
                        p.getRating().getRate()
                ))
                .user(new UserDto(
                        u.getId(),
                        u.getName()
                ))
                .build();
    }


    public Order orderDtoToOrder(OrderDto o) {
        Product productInsert = productRepository.findById(o.getProduct().getId()).orElse(null);
        User userInsert = userRepository.findById(o.getUser().getId()).orElse(null);

        return Order.builder()
                .id(o.getId())
                .product(productInsert)
                .user(userInsert)
                .build();
    }

//    public class OrderDto {
//        private Long id;
//        private ProductDto product;
//        private UserDto user;
}
