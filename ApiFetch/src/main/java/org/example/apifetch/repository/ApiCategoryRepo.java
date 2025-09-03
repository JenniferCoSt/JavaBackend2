package org.example.apifetch.repository;

import org.example.apifetch.model.CategoryApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiCategoryRepo extends JpaRepository<CategoryApi, Integer> {

}
