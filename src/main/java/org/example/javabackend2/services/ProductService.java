package org.example.javabackend2.services;

import org.example.javabackend2.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    public List<ProductDto> getAllProducts();
}
