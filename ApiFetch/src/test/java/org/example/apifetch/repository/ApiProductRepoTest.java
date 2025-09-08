package org.example.apifetch.repository;

import org.example.apifetch.model.CategoryApi;
import org.example.apifetch.model.ProductApi;
import org.example.apifetch.model.RatingApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.containers.MySQLContainer;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ApiProductRepoTest {


    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0-bookworm");

    @Autowired
    ApiProductRepo productRepo;

    @Autowired
    ApiCategoryRepo categoryRepo;

//    @Test
//    void contextLoads() {
//    }

    @Test
    void save_and_load_categories_from_database() {

        CategoryApi cat1 = new CategoryApi();
        cat1.setType("electronics");
        cat1 = categoryRepo.save(cat1);

        CategoryApi found1 = categoryRepo.findById(cat1.getId()).orElseThrow();
        assertThat(found1.getType()).isEqualTo("electronics");
        assertThat(found1.getType()).isNotEqualTo("plushies");


        CategoryApi cat2 = new CategoryApi();
        cat2.setType("jewelery");
        cat2 = categoryRepo.save(cat2);

        CategoryApi found2 = categoryRepo.findById(cat2.getId()).orElseThrow();
        assertThat(found2.getType()).isEqualTo("jewelery");
        assertThat(found2.getType()).isNotEqualTo("electronic");


    }

    @Test
    void save_and_load_products_from_database() {


        //behövs för att separera test mot cat.repo och prod.repo
        //kan ej spara en produkt utan category
        CategoryApi cat1 = new CategoryApi();
        cat1.setType("electronics");

        CategoryApi cat2 = new CategoryApi();
        cat2.setType("jewelery");


        ProductApi p1 = new ProductApi();
        p1.setId(10001L);
        p1.setTitle("Wireless Headphones");
        p1.setDescription("Over-ear Bluetooth headphones with 30h battery.");
        p1.setPrice(79.99);
        //vi sparar rating och category olika, borde vi det?
        p1.setRating(new RatingApi(4.2, 314));
        p1.setCategory(cat1);
        p1.setImage("https://picsum.photos/seed/phones1/600/400");
        productRepo.save(p1);

        ProductApi found = productRepo.findById(p1.getId()).orElseThrow();
        assertThat(found.getTitle()).isEqualTo("Wireless Headphones");
        assertThat(found.getTitle()).isNotEqualTo("Wireless Headphone");
        assertThat(found.getCategory().getType()).isEqualTo("electronics");
    }

}
