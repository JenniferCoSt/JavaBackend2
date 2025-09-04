package org.example.apifetch.repository;

import org.example.apifetch.model.CategoryApi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApiCategoryRepo extends JpaRepository<CategoryApi, Integer> {

    public CategoryApi findByType(String CategoryTitel);

}
