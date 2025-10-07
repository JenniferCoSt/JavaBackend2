package org.example.apifetch.service;

import org.assertj.core.api.AssertionsForClassTypes;
import org.example.apifetch.repository.ApiCategoryRepo;
import org.example.apifetch.repository.ApiProductRepo;
import org.example.apifetch.service.impl.ApiProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ApiProductServiceIntegrationTest {

    @MockitoBean
    HttpClient httpClient;

    @Mock
    HttpResponse<String> response;

    @Autowired
    ApiProductServiceImpl productService;

    @Autowired
    ApiCategoryService categoryService;

    @Autowired
    ApiProductRepo productRepo;

    @Autowired
    ApiCategoryRepo categoryRepo;

    @Test
    void load_products_from_service() throws IOException, InterruptedException {

        String expectedProduct = """
                {
                "id": 1,
                "title": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                "price": 109.95,
                "description": "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                "category": "men's clothing",
                "rating": {
            "rate": 3.9,
                    "count": 120 }
                }
            """;


        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(response);
        when(response.body()).thenReturn(expectedProduct);

        int productsBeforeLoad = productRepo.findAll().size();
        int categoryBeforeLoad = categoryRepo.findAll().size();

        productService.fetchProductsFromApi();

        assertThat(productRepo.findAll()).isNotEmpty();
        assertThat(categoryRepo.findAll()).isNotEmpty();

        AssertionsForClassTypes.assertThat(productsBeforeLoad).isLessThan(productRepo.findAll().size());
        AssertionsForClassTypes.assertThat(categoryBeforeLoad).isLessThan(categoryRepo.findAll().size());
    }
}
