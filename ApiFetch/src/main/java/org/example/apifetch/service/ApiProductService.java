package org.example.apifetch.service;

import org.example.apifetch.Dto.ProductDtoApi;
import org.example.apifetch.Dto.RatingDtoApi;
import org.example.apifetch.model.ProductApi;
import org.example.apifetch.model.RatingApi;

public interface ApiProductService {

    public void fetchProductsFromApi();

    public ProductApi productDtoToProduct(ProductDtoApi productDto);

    public void saveProducts(ProductApi product);

    public RatingApi ratingDtoToRating(RatingDtoApi ratingDto);

}
