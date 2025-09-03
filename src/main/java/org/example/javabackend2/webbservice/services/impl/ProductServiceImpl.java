package org.example.javabackend2.webbservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.webbservice.dtos.ProductDto;
import org.example.javabackend2.webbservice.mappers.ProductMapper;
import org.example.javabackend2.webbservice.repos.ProductRepository;
import org.example.javabackend2.webbservice.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::productToProductDto).toList();
    }

    @Override
    public ProductDto getProductById(long id) {
        return productRepository.findById(id).map(productMapper::productToProductDto).orElse(null);
    }

}
