package org.example.javabackend2.webbservice.services;

import org.example.javabackend2.webbservice.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    public List<ProductDto> getAllProducts();
}
