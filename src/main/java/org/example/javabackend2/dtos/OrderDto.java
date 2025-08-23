package org.example.javabackend2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private ProductDto product;
    private UserDto user;
}
