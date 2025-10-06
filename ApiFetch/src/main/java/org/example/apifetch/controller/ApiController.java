package org.example.apifetch.controller;

import org.example.apifetch.service.ApiCategoryService;
import org.example.apifetch.service.ApiProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final ApiCategoryService apiCategoryServices;
    private final ApiProductService apiProductServices;

    public ApiController(ApiCategoryService apiCategoryServices, ApiProductService apiProductServices){
        this.apiCategoryServices = apiCategoryServices;
        this.apiProductServices = apiProductServices;
    }

    @PostMapping("/loadapi")
    public void loadApi() {
        apiProductServices.fetchProductsFromApi();
    }
}
