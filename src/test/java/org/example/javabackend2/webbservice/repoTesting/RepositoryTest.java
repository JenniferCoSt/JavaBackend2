package org.example.javabackend2.webbservice.repoTesting;

import org.example.javabackend2.webbservice.models.Category;
import org.example.javabackend2.webbservice.models.Product;
import org.example.javabackend2.webbservice.models.Rating;
import org.example.javabackend2.webbservice.repos.CategoryRepository;
import org.example.javabackend2.webbservice.repos.ProductRepository;
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
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoryTest {


    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0-bookworm");

    @Autowired
    ProductRepository productRepo;

    @Autowired
    CategoryRepository categoryRepo;

    @Test
    void save_and_load_categories_from_database_repo() {

        Category cat1 = new Category();
        cat1.setType("electronics");
        cat1 = categoryRepo.save(cat1);

        Category found1 = categoryRepo.findById(cat1.getId()).orElseThrow();
        assertThat(found1.getType()).isEqualTo("electronics");
        assertThat(found1.getType()).isNotEqualTo("plushies");


        Category cat2 = new Category();
        cat2.setType("jewelery");
        cat2 = categoryRepo.save(cat2);


        Category found2 = categoryRepo.findById(cat2.getId()).orElseThrow();
        assertThat(found2.getType()).isEqualTo("jewelery");
        assertThat(found2.getType()).isNotEqualTo("electronic");

        assertThat(categoryRepo.findAll()).hasSize(2);

    }

    @Test
    void save_and_load_products_from_database_repo() {

        //behövs för att separera test mot cat.repo och prod.repo
        //kan ej spara en produkt utan category
        Category cat1 = new Category();
        cat1.setType("electronics");
        cat1 = categoryRepo.save(cat1);


        Product p1 = new Product();
        p1.setId(10001L);
        p1.setTitle("Wireless Headphones");
        p1.setDescription("Over-ear Bluetooth headphones with 30h battery.");
        p1.setPrice(79.99);
        p1.setRating(new Rating(4.2, 314));
        p1.setCategory(cat1);
        p1.setImage("https://picsum.photos/seed/phones1/600/400");
        productRepo.save(p1);

        Product found = productRepo.findById(p1.getId()).orElseThrow();
        assertThat(found.getTitle()).isEqualTo("Wireless Headphones");
        assertThat(found.getTitle()).isNotEqualTo("Wireless Headphone");
        assertThat(found.getCategory().getType()).isEqualTo("electronics");
        assertThat(found.getPrice()).isEqualTo(79.99);
        assertThat(found.getDescription()).isNotEqualTo("Over-ear Bluetooth headphones with 40h battery.");
        assertThat(found.getDescription()).isEqualTo("Over-ear Bluetooth headphones with 30h battery.");
        assertThat(productRepo.findAll()).hasSize(1);
    }

    @Test
    void save_and_delete_products_from_database_repo() {

        Category cat1 = new Category();
        cat1.setType("electronics");
        cat1 = categoryRepo.save(cat1);

        Product p1 = new Product();
        p1.setId(10001L);
        p1.setTitle("Wireless Headphones");
        p1.setDescription("Over-ear Bluetooth headphones with 30h battery.");
        p1.setPrice(79.99);
        p1.setRating(new Rating(4.2, 314));
        p1.setCategory(cat1);
        p1.setImage("https://picsum.photos/seed/phones1/600/400");
        productRepo.save(p1);

        Product p2 = new Product();
        p2.setId(10002L);
        p2.setTitle("27\" 4K Monitor");
        p2.setDescription("27-inch 4K IPS, HDR10, 60Hz.");
        p2.setPrice(269.00);
        p2.setRating(new Rating(4.5, 128));
        p2.setCategory(cat1);
        p2.setImage("https://picsum.photos/seed/monitor4k/600/400");
        productRepo.save(p2);

        assertThat(productRepo.findAll()).hasSize(2);
        productRepo.delete(p1);
        assertThat(productRepo.findAll()).hasSize(1);
        productRepo.delete(p2);
        assertThat(productRepo.findAll()).hasSize(0);

    }


}

