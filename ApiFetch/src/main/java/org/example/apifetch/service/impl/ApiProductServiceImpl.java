package org.example.apifetch.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifetch.service.ApiCategoryService;
import org.example.apifetch.service.ApiProductService;
import org.example.apifetch.Dto.CategoryDtoApi;
import org.example.apifetch.Dto.ProductDtoApi;
import org.example.apifetch.Dto.RatingDtoApi;
import org.example.apifetch.model.CategoryApi;
import org.example.apifetch.model.ProductApi;
import org.example.apifetch.model.RatingApi;
import org.example.apifetch.repository.ApiCategoryRepo;
import org.example.apifetch.repository.ApiProductRepo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Service
public class ApiProductServiceImpl implements ApiProductService {

    private final ApiCategoryRepo catRepo;
    private final ApiProductRepo prodRepo;

    private final ApiCategoryService catServices;

    public ApiProductServiceImpl(ApiCategoryRepo catRepo, ApiProductRepo prodRepo, ApiCategoryService catServices) {
        this.catRepo = catRepo;
        this.prodRepo = prodRepo;
        this.catServices = catServices;
    }


    public void fetchProductsFromApi() {

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://fakestoreapi.com/products"))
                    .GET()
                    .build();


            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            List<ProductDtoApi> productsDtos = Arrays.asList(mapper.readValue(response.body(), ProductDtoApi[].class));

            createCategorys(productsDtos);
            saveProducts(productsDtos);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ProductApi productDtoToProduct(ProductDtoApi productDto) {

        CategoryApi category = catServices.getCategoryFromTitel(productDto.getCategory());

        return ProductApi.builder()
                .id(productDto.getId())
                .title(productDto.getTitle())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .category(category)
                .rating(ratingDtoToRating(productDto.getRating()))
                .image(productDto.getImage())
                .build();
    }

    public void createCategorys(List<ProductDtoApi> productDtos) {
        List<String> newCategorys = productDtos.stream().map(ProductDtoApi::getCategory).distinct().toList();
        newCategorys.forEach(c -> catServices.addCategory(CategoryDtoApi.builder().type(c).build()));
    }


    public void saveProducts(List<ProductDtoApi> productDtos) {
        List<ProductApi> productEntitys = productDtos.stream().map(this::productDtoToProduct).toList();
        prodRepo.saveAll(productEntitys);
    }

    public RatingApi ratingDtoToRating(RatingDtoApi ratingDto) {
        return RatingApi.builder().rate(ratingDto.getRate()).count(ratingDto.getCount()).build();
    }
}