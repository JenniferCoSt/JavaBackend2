package org.example.apifetch.repository;

import org.example.apifetch.model.CategoryApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ApiCategoryRepoTest {

    @Autowired
    ApiCategoryRepo categoryRepo;

    CategoryApi cat1;
    CategoryApi cat2;

    @BeforeEach
    void setUp() {
        cat1 = CategoryApi.builder()
                .type("electronics").build();

        cat2 = CategoryApi.builder()
                .type("jewelery").build();
    }

    @Test
    void save_and_load_categories_from_database_repo() {

        CategoryApi savedCat1 = categoryRepo.save(cat1);

        CategoryApi savedCat2 = categoryRepo.save(cat2);

        assertThat(savedCat1).isEqualTo(cat1);
        assertThat(savedCat2).isEqualTo(cat2);
        assertThat(categoryRepo.findAll()).hasSize(2);
    }

    @Test
    void categoryRepo_findByType_returnCategoryApi() {
        categoryRepo.save(cat1);
        categoryRepo.save(cat2);

        CategoryApi result1 = categoryRepo.findByType(cat1.getType());
        assertThat(result1.getType()).isEqualTo("electronics");
        assertThat(result1.getType()).isNotEqualTo("plushies");
        assertThat(result1).isNotNull();

        CategoryApi result2 = categoryRepo.findByType(cat2.getType());
        assertThat(result2.getType()).isEqualTo("jewelery");
        assertThat(result2.getType()).isNotEqualTo("electronic");
        assertThat(result2).isNotNull();

    };
}
