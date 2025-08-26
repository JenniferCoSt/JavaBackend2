package org.example.javabackend2.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.dtos.ProductDto;
import org.example.javabackend2.mappers.ProductMapper;
import org.example.javabackend2.repos.ProductRepository;
import org.example.javabackend2.services.ProductService;
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

}
