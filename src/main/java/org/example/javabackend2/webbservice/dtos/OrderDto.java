package org.example.javabackend2.webbservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private ProductDto product;
    private UserDto user;
}
