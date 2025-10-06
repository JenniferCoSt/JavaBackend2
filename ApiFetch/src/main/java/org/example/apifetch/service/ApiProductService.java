package org.example.apifetch.service;

import org.example.apifetch.Dto.ProductDtoApi;
import org.example.apifetch.Dto.RatingDtoApi;
import org.example.apifetch.model.ProductApi;
import org.example.apifetch.model.RatingApi;

import java.util.List;

public interface ApiProductService {

    public void fetchProductsFromApi();

    public ProductApi productDtoToProduct(ProductDtoApi productDto);

    public void saveProducts(List<ProductDtoApi> productDtos);

    public RatingApi ratingDtoToRating(RatingDtoApi ratingDto);

    public void createCategorys(List<ProductDtoApi> productDtos);

}
