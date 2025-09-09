package org.example.apifetch.repository.serviceTest;

import org.example.apifetch.ApiServices.ApiProductServices;
import org.example.apifetch.ApiServices.ApiProductServices;
import org.example.apifetch.ApiServices.impl.ApiProductServicesImpl;
import org.example.apifetch.model.CategoryApi;
import org.example.apifetch.model.ProductApi;
import org.example.apifetch.model.RatingApi;
import org.example.apifetch.repository.ApiCategoryRepo;
import org.example.apifetch.repository.ApiProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.containers.MySQLContainer;

import static org.assertj.core.api.Assertions.assertThat;
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ApiServiceTest {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0-bookworm");

    @Autowired
    ApiProductServices productService;

    @Autowired
    ApiProductRepo productRepo;

    @Autowired
    ApiCategoryRepo categoryRepo;

    @Test
    void load_products_from_service() {

        int productsBeforeLoad = productRepo.findAll().size();
        int categoryBeforeLoad = categoryRepo.findAll().size();

        productService.saveProductsFromApi();

        assertThat(productRepo.findAll()).isNotEmpty();
        assertThat(categoryRepo.findAll()).isNotEmpty();

        assertThat(productsBeforeLoad).isLessThan(productRepo.findAll().size());
        assertThat(categoryBeforeLoad).isLessThan(categoryRepo.findAll().size());
    }
}
