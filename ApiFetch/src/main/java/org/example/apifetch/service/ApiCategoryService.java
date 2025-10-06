package org.example.apifetch.service;

import org.example.apifetch.Dto.CategoryDtoApi;
import org.example.apifetch.model.CategoryApi;

public interface ApiCategoryService {

    public CategoryApi getCategoryFromTitel(String categoryTitel);

    public void addCategory(CategoryDtoApi category);

    public CategoryApi getCategoryFromDto(CategoryDtoApi category);

}
