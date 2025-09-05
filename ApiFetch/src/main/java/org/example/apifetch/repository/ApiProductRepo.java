package org.example.apifetch.repository;


import org.example.apifetch.model.ProductApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiProductRepo extends JpaRepository<ProductApi, Integer> {

}
