package org.example.apifetch.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DataJdbcTest
public class ApiProductRepoTest {

    @Autowired
    ApiProductRepo apPrRepo;

    @Container
    MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.36");
}
