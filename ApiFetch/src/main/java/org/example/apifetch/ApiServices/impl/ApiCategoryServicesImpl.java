package org.example.apifetch.ApiServices.impl;

import org.example.apifetch.ApiServices.ApiCategoryServices;
import org.example.apifetch.Dto.CategoryDtoApi;
import org.example.apifetch.model.CategoryApi;
import org.example.apifetch.repository.ApiCategoryRepo;
import org.example.apifetch.repository.ApiProductRepo;
import org.springframework.stereotype.Service;

@Service
public class ApiCategoryServicesImpl implements ApiCategoryServices {

    private final ApiCategoryRepo catRepo;
    private final ApiProductRepo prodRepo;

    public ApiCategoryServicesImpl(ApiCategoryRepo catRepo, ApiProductRepo prodRepo) {
        this.catRepo = catRepo;
        this.prodRepo = prodRepo;
    }


    @Override
    public CategoryApi getCategoryFromTitel(String categoryTitel){
        return catRepo.findByType(categoryTitel);
    }

    @Override
    public void addCategory(CategoryDtoApi category){

        CategoryApi pressentCategory = getCategoryFromTitel(category.getType());

        if(pressentCategory == null){

            //här borde vi köra dtoToEntety
            CategoryApi newCategory = getCategoryFromDto(category);

            catRepo.save(newCategory);
            System.out.println("new category "+ category.getType() +" added");
        }
    }

    @Override
    public CategoryApi getCategoryFromDto(CategoryDtoApi category) {
        return CategoryApi.builder().type(category.getType()).build();
    }




















}
