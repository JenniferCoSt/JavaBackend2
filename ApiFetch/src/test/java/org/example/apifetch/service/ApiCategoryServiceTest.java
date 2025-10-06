package org.example.apifetch.service;

import org.example.apifetch.Dto.CategoryDtoApi;
import org.example.apifetch.repository.ApiCategoryRepo;
import org.example.apifetch.service.impl.ApiCategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ApiCategoryServiceTest {

    @InjectMocks
    private ApiCategoryServiceImpl apiCategoryService;

    @Mock
    private ApiCategoryRepo categoryRepo;

    CategoryDtoApi categoryDtoApi1;
    CategoryDtoApi categoryDtoApi2;

    @BeforeEach
    void setUp() {
        categoryDtoApi1 = CategoryDtoApi.builder()
                .id(1L)
                .type("food")
                .build();

        categoryDtoApi2 = CategoryDtoApi.builder()
                .id(2L)
                .type("animal")
                .build();
    }


}
