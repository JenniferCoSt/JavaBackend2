package org.example.javabackend2.webbservice.serviceTesting;

import org.example.javabackend2.webbservice.dtos.ProductDto;
import org.example.javabackend2.webbservice.mappers.ProductMapper;
import org.example.javabackend2.webbservice.models.Product;
import org.example.javabackend2.webbservice.repos.ProductRepository;
import org.example.javabackend2.webbservice.services.ProductService;
import org.example.javabackend2.webbservice.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(productRepository, productMapper);
    }

    @Test
    void getAllProducts_ShouldReturnAllProducts() {
        Product product1 = Product.builder().id(1L).title("title1").description("description1").build();
        Product product2 = Product.builder().id(2L).title("title2").description("description2").build();
        List<Product> products = Arrays.asList(product1, product2);

        ProductDto dto1 = ProductDto.builder().id(1L).title("title1").description("description1").build();
        ProductDto dto2 = ProductDto.builder().id(2L).title("title2").description("description2").build();

        when(productRepository.findAll()).thenReturn(products);
        when(productMapper.productToProductDto(product1)).thenReturn(dto1);
        when(productMapper.productToProductDto(product2)).thenReturn(dto2);

        List<ProductDto> result = productService.getAllProducts();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("title1", result.get(0).getTitle());
        Assertions.assertEquals("description1", result.get(0).getDescription());
        Assertions.assertEquals("title2", result.get(1).getTitle());
        Assertions.assertEquals("description2", result.get(1).getDescription());
    }

    @Test
    void getProductById_ShouldReturnProduct() {
        Product product = Product.builder().id(1L).title("title").description("description").build();
        ProductDto dto = ProductDto.builder().id(1L).title("title").description("description").build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.productToProductDto(product)).thenReturn(dto);

        ProductDto result = productService.getProductById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("title", result.getTitle());
        Assertions.assertEquals("description", result.getDescription());
    }

    @Test
    void getFeaturedProducts_ShouldReturnTop8Products() {
        List<Product> products = Arrays.asList(
                Product.builder().id(1L).title("A title").build(),
                Product.builder().id(2L).title("B title").build(),
                Product.builder().id(3L).title("C title").build(),
                Product.builder().id(4L).title("D title").build(),
                Product.builder().id(5L).title("E title").build(),
                Product.builder().id(6L).title("F title").build(),
                Product.builder().id(7L).title("G title").build(),
                Product.builder().id(8L).title("H title").build()
        );

        when(productRepository.findTop8ByOrderByTitleAsc()).thenReturn(products);

        List<Product> result = productService.getFeaturedProducts();

        Assertions.assertEquals(8, result.size());
        Assertions.assertEquals("A title", result.get(0).getTitle());
        Assertions.assertEquals("B title", result.get(1).getTitle());
        Assertions.assertEquals("H title", result.get(7).getTitle());
    }
}
