package org.example.javabackend2.webbservice.repos;

import org.example.javabackend2.webbservice.models.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
