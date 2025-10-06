package org.example.apifetch.service;

import org.example.apifetch.Dto.CategoryDtoApi;
import org.example.apifetch.model.CategoryApi;
import org.example.apifetch.repository.ApiCategoryRepo;
import org.example.apifetch.service.impl.ApiCategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApiCategoryServiceTest {

    @InjectMocks
    private ApiCategoryServiceImpl apiCategoryService;

    @Mock
    private ApiCategoryRepo categoryRepo;

    CategoryDtoApi categoryDtoApi1;
    CategoryDtoApi categoryDtoApi2;

    CategoryApi categoryApi1;
    CategoryApi categoryApi2;

    @BeforeEach
    void setUp() {
        categoryDtoApi1 = CategoryDtoApi.builder()
                .type("food")
                .build();

        categoryDtoApi2 = CategoryDtoApi.builder()
                .type("animal")
                .build();

        categoryApi1 = CategoryApi.builder()
                .type("food")
                .build();

        categoryApi2 = CategoryApi.builder()
                .type("animal")
                .build();
    }

    @Test
    void categoryService_getCategoryFromTitel_returnCategory(){
        when(categoryRepo.findByType("food")).thenReturn(categoryApi1);
        when(categoryRepo.findByType("animal")).thenReturn(categoryApi2);

        CategoryApi result1 = apiCategoryService.getCategoryFromTitel("food");
        CategoryApi result2 = apiCategoryService.getCategoryFromTitel("animal");

        Assertions.assertEquals(categoryApi1, result1);
        Assertions.assertEquals(categoryApi2, result2);
    }

    @Test
    void categoryService_addCategory_success(){
        when(categoryRepo.findByType("food")).thenReturn(null);
        when(categoryRepo.findByType("animal")).thenReturn(null);

        apiCategoryService.addCategory(categoryDtoApi1);
        apiCategoryService.addCategory(categoryDtoApi2);

        verify(categoryRepo, times(2)).save(any(CategoryApi.class));
    }

    @Test
    void categoryService_convertFromDto_returnEntity(){
        CategoryApi result1 = apiCategoryService.getCategoryFromDto(categoryDtoApi1);
        CategoryApi result2 = apiCategoryService.getCategoryFromDto(categoryDtoApi2);

        Assertions.assertEquals(categoryApi1, result1);
        Assertions.assertEquals(categoryApi2, result2);
    }
}
