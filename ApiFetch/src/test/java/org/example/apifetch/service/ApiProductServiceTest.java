package org.example.apifetch.service;

import org.example.apifetch.Dto.ProductDtoApi;
import org.example.apifetch.Dto.RatingDtoApi;
import org.example.apifetch.model.CategoryApi;
import org.example.apifetch.model.ProductApi;
import org.example.apifetch.model.RatingApi;
import org.example.apifetch.repository.ApiCategoryRepo;
import org.example.apifetch.repository.ApiProductRepo;
import org.example.apifetch.service.impl.ApiProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension .class)
class ApiProductServiceTest {

    @InjectMocks
    ApiProductServiceImpl productService;

    @Mock
    ApiCategoryService categoryService;

    @Mock
    ApiProductRepo productRepo;

    @Mock
    ApiCategoryRepo categoryRepo;

    private ProductApi productApi1;
    private ProductApi productApi2;
    private CategoryApi categoryApi1;
    private CategoryApi categoryApi2;
    private RatingApi ratingApi1;
    private RatingApi ratingApi2;

    private ProductDtoApi productDtoApi1;
    private ProductDtoApi productDtoApi2;

    private RatingDtoApi ratingDtoApi1;
    private RatingDtoApi ratingDtoApi2;

    List<ProductDtoApi> productDtos;
    List<ProductApi> productApiList;

    @BeforeEach
    void setUp() {

        ratingDtoApi1 = RatingDtoApi.builder()
                .rate(3.5)
                .count(37)
                .build();

        ratingDtoApi2 = RatingDtoApi.builder()
                .rate(4.8)
                .count(29)
                .build();

        productDtoApi1 = ProductDtoApi.builder()
                .id(11L)
                .title("Apple")
                .description("It's red")
                .price(5.5)
                .rating(ratingDtoApi1)
                .category("food")
                .image("apple.jpg")
                .build();

        productDtoApi2 = ProductDtoApi.builder()
                .id(22L)
                .title("Bird")
                .description("It can fly")
                .price(145.0)
                .rating(ratingDtoApi2)
                .category("animal")
                .image("bird.jpg")
                .build();

        productDtos = List.of(productDtoApi1, productDtoApi2);

        categoryApi1 = CategoryApi.builder()
                .id(1L)
                .type("food")
                .build();

        categoryApi2 = CategoryApi.builder()
                .id(2L)
                .type("animal")
                .build();

        ratingApi1 = RatingApi.builder()
                .rate(3.5)
                .count(37)
                .build();

        ratingApi2 = RatingApi.builder()
                .rate(4.8)
                .count(29)
                .build();


        productApi1 = ProductApi.builder()
                .id(11L)
                .title("Apple")
                .description("It's red")
                .price(5.5)
                .rating(ratingApi1)
                .category(categoryApi1)
                .image("apple.jpg")
                .build();

        productApi2 = ProductApi.builder()
                .id(22L)
                .title("Bird")
                .description("It can fly")
                .price(145.0)
                .rating(ratingApi2)
                .category(categoryApi2)
                .image("bird.jpg")
                .build();

        productApiList = List.of(productApi1, productApi2);
    }

    @Test
    void ApiService_convertFromDto_returnEntity(){
        when(categoryService.getCategoryFromTitel("food")).thenReturn(categoryApi1);
        when(categoryService.getCategoryFromTitel("animal")).thenReturn(categoryApi2);

        ProductApi result1 = productService.productDtoToProduct(productDtoApi1);
        ProductApi result2 = productService.productDtoToProduct(productDtoApi2);

        Assertions.assertThat(result1).isEqualTo(productApi1);
        Assertions.assertThat(result2).isEqualTo(productApi2);
    }

    @Test
    void ApiService_saveProducts_success() {
        when(categoryService.getCategoryFromTitel("food")).thenReturn(categoryApi1);
        when(categoryService.getCategoryFromTitel("animal")).thenReturn(categoryApi2);

        productService.saveProducts(productDtos);

        verify(productRepo).saveAll(productApiList);

    }

    @Test
    void ApiService_convertRatingDto_toRatingEntity() {
        RatingApi result1 = productService.ratingDtoToRating(ratingDtoApi1);
        RatingApi result2 = productService.ratingDtoToRating(ratingDtoApi2);

        Assertions.assertThat(result1).isEqualTo(ratingApi1);
        Assertions.assertThat(result2).isEqualTo(ratingApi2);


    }

}
