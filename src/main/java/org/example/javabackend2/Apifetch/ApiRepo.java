package org.example.javabackend2.Apifetch;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ApiRepo extends JpaRepository<Product, Integer> {

}
