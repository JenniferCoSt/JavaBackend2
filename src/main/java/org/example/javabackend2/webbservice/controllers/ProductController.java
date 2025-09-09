package org.example.javabackend2.webbservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.webbservice.dtos.OrderDto;
import org.example.javabackend2.webbservice.dtos.ProductDto;
import org.example.javabackend2.webbservice.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping({ "/products"})
    public String listProducts(Model model) {
        List<ProductDto> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/product/{id}")
    public String listOneProduct(Model model, @PathVariable long id
    , @RequestParam(defaultValue = "false") boolean placeorder) {
        ProductDto product = productService.getProductById(id);

        model.addAttribute("product", product);
        model.addAttribute("placeorder", placeorder);
        return "productView";
    }
}