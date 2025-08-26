package org.example.javabackend2.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String categoryName;
    private Double rating;
}
