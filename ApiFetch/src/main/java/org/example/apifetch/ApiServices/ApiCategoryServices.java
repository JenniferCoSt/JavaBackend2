package org.example.apifetch.ApiServices;

import org.example.apifetch.Dto.CategoryDtoApi;
import org.example.apifetch.model.CategoryApi;

public interface ApiCategoryServices {

    public CategoryApi getCategoryFromTitel(String categoryTitel);

    public void addCategory(CategoryDtoApi category);

    public CategoryApi getCategoryFromDto(CategoryDtoApi category);

}
