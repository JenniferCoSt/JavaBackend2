package org.example.javabackend2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String title;
    private double price;
}
