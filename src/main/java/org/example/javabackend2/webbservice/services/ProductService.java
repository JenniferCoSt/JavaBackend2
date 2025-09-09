package org.example.javabackend2.webbservice.services;

import org.example.javabackend2.webbservice.dtos.ProductDto;
import org.example.javabackend2.webbservice.models.Product;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();
    ProductDto getProductById(long id);

    List<Product> getFeaturedProducts();
}
