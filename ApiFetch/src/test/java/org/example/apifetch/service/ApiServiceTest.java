package org.example.apifetch.service;

import org.example.apifetch.Dto.CategoryDtoApi;
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
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension .class)
class ApiServiceTest {

    @InjectMocks
    ApiProductServiceImpl productService;

    @Mock
    ApiProductRepo productRepo;

    @Mock
    ApiCategoryRepo categoryRepo;

    private ProductApi productApi;
    private CategoryApi categoryApi;
    private RatingApi ratingApi;

    private ProductDtoApi productDtoApi;
    private CategoryDtoApi categoryDtoApi;
    private RatingDtoApi ratingDtoApi;

    @BeforeEach
    void setUp() {
        CategoryDtoApi categoryDtoApi = new CategoryDtoApi();


    }

    @Test
    void ApiService_saveProducts_success() {

        int productsBeforeLoad = productRepo.findAll().size();
        int categoryBeforeLoad = categoryRepo.findAll().size();

        productService.fetchProductsFromApi();

        assertThat(productRepo.findAll()).isNotEmpty();
        assertThat(categoryRepo.findAll()).isNotEmpty();

        assertThat(productsBeforeLoad).isLessThan(productRepo.findAll().size());
        assertThat(categoryBeforeLoad).isLessThan(categoryRepo.findAll().size());
    }
}
