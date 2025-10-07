package org.example.javabackend2.webbservice.controllerTesting;
import org.example.javabackend2.webbservice.controllers.OrderController;
import org.example.javabackend2.webbservice.controllers.ProductController;
import org.example.javabackend2.webbservice.dtos.OrderDto;
import org.example.javabackend2.webbservice.dtos.ProductDto;
import org.example.javabackend2.webbservice.dtos.UserDto;
import org.example.javabackend2.webbservice.services.OrderService;
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
        var p1 = ProductDto.builder().id(1L).title("Apple").build();
        var p2 = ProductDto.builder().id(2L).title("Banana").build();
        var products = List.of(p1, p2);
        when(productService.getAllProducts()).thenReturn(products);

        // when
        String view = controller.listProducts(model);

        // then
        assertThat(view).isEqualTo("products");
        assertThat(model.getAttribute("products")).isEqualTo(products);
        verify(productService).getAllProducts();
        verifyNoMoreInteractions(productService);
    }


    /*


    @GetMapping("/product/{id}")
    public String listOneProduct(Model model, @PathVariable long id
    , @RequestParam(defaultValue = "false") boolean placeorder) {
        ProductDto product = productService.getProductById(id);

        model.addAttribute("product", product);
        model.addAttribute("placeorder", placeorder);
        return "productView";
    }
     */
}
