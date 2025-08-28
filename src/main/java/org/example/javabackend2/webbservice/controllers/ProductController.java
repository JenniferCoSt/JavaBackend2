package org.example.javabackend2.webbservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.webbservice.dtos.ProductDto;
import org.example.javabackend2.webbservice.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping({ "/products"})
    public String listProducts(Model model) {
        List<ProductDto> products = productService.getAllProducts();
       // for (ProductDto product : products) {
       //     System.out.println(product.getTitle());
       // }
        //if (products.isEmpty()) {
        //    System.out.println("empty list");
        //}
        model.addAttribute("products", products);
        return "products";
    }
}