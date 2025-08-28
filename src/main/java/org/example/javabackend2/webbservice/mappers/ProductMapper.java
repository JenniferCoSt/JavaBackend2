package org.example.javabackend2.webbservice.mappers;

import org.example.javabackend2.webbservice.dtos.ProductDto;
import org.example.javabackend2.webbservice.models.Product;
import org.springframework.stereotype.Service;


@Service
public class ProductMapper {

    public ProductDto productToProductDto(Product p) {
        return ProductDto.builder()
                .id(p.getId())
                .title(p.getTitle())
                .description(p.getDescription())
                .price(p.getPrice())
                .image(p.getImage())
                .categoryName(String.valueOf(p.getCategory().getType()))
                .rating(p.getRating().getRate())
                .build();
    }


    /*
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String categoryName;
    private Double rating;
    private Integer ratingCount;
     */
}
