package org.example.apifetch.ApiServices;

import org.example.apifetch.Dto.ProductDtoApi;
import org.example.apifetch.Dto.RatingDtoApi;
import org.example.apifetch.model.CategoryApi;
import org.example.apifetch.model.ProductApi;
import org.example.apifetch.model.RatingApi;

public interface ApiProductServices {

    public void saveProductsFromApi();

    public ProductApi productDtoToProduct(ProductDtoApi productDto);

    public void saveProducts(ProductApi product);

    public RatingApi ratingDtoToRating(RatingDtoApi ratingDto);








}
