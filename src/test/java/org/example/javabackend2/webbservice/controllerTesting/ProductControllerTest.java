package org.example.javabackend2.webbservice.controllerTesting;
import org.example.javabackend2.webbservice.controllers.ProductController;
import org.example.javabackend2.webbservice.dtos.ProductDto;
import org.example.javabackend2.webbservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    ProductService productService;
    @InjectMocks
    ProductController controller;

    @Test
    void listProducts_and_returns_productView() {
        Model model = new ExtendedModelMap();

        // given
        ProductDto p1 = ProductDto.builder().id(1L).title("Jacket").build();
        ProductDto p2 = ProductDto.builder().id(2L).title("Jillet").build();
        List<ProductDto> products = List.of(p1, p2);
        when(productService.getAllProducts()).thenReturn(products);

        // when
        String view = controller.listProducts(model);

        // then
        assertThat(view).isEqualTo("products");
        assertThat(model.getAttribute("products")).isEqualTo(products);
        verify(productService).getAllProducts();
        verifyNoMoreInteractions(productService);
    }

    @Test
    void listOneProduct_and_returns_productView() {
        Model model = new ExtendedModelMap();

        //given
        ProductDto p1 = ProductDto.builder().id(1L).title("Jacket").build();
        when(productService.getProductById(1L)).thenReturn(p1);

        //when
        String view = controller.listOneProduct(model, 1L, false);

        //then
        assertThat(view).isEqualTo("productView");
        assertThat(model.getAttribute("product")).isEqualTo(p1);
        assertThat(model.getAttribute("placeorder")).isEqualTo(false);
        verify(productService).getProductById(1L);
        verifyNoMoreInteractions(productService);
    }

}
