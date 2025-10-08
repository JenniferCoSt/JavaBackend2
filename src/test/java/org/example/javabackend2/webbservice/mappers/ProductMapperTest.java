package org.example.javabackend2.webbservice.mappers;

import org.example.javabackend2.webbservice.dtos.ProductDto;
import org.example.javabackend2.webbservice.models.Category;
import org.example.javabackend2.webbservice.models.Product;
import org.example.javabackend2.webbservice.models.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    ProductMapper productMapper;
    Product product1;
    Product product2;
    ProductDto productDto1;
    ProductDto productDto2;
    Rating rating1;
    Rating rating2;
    Category category1;
    Category category2;

    @BeforeEach
    void setUp() {
        category1 = Category.builder()
                .type("Category1")
                .build();

        category2 = Category.builder()
                .type("Category2")
                .build();

        rating1 = Rating.builder()
                .rate(1.1)
                .count(1)
                .build();

        rating2 = Rating.builder()
                .rate(2.2)
                .count(2)
                .build();

        product1 = Product.builder()
                .title("tröjja")
                .description("en tröjja")
                .price(77)
                .rating(rating1)
                .category(category1)
                .image("Bild1")
                .build();

        product2 = Product.builder()
                .title("byxa")
                .description("en byxa")
                .price(99)
                .rating(rating2)
                .category(category2)
                .image("Bild2")
                .build();

        productDto1 = ProductDto.builder()
                .title()
                .description()
                .price()
                .rating()
                .category()
                .image()
                .build();

        productDto2 = ProductDto.builder()
                .title()
                .description()
                .price()
                .rating()
                .category()
                .image()
                .build();


    }

    @Test
    void productToProductDto() {


    }
}