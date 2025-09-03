package org.example.apifetch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifetch.model.CategoryApi;
import org.example.apifetch.model.ProductApi;
import org.example.apifetch.repository.ApiRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ApiController {

    private final ApiRepo repo;
    public ApiController(ApiRepo repo) {
        this.repo = repo;
    }

    public void addProduct(ProductApi product) {
        repo.save(product);
    }

    @RequestMapping("loadapi")
    public void saveProducts() throws IOException, InterruptedException { //kör try with res
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://fakestoreapi.com/products"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        List<ProductApi> products = Arrays.asList(
                mapper.readValue(response.body(), ProductApi[].class)
        );

        System.out.println("Start!");

        List<CategoryApi> categorys = new ArrayList<>();
        categorys.add((CategoryApi) products.stream().map(ProductApi::getCategory).distinct());
        categorys.forEach(System.out::println);
//        repo.saveAll(categorys)

        System.out.println("Stop!");


//        repo.saveAll(products.stream().map(ProductApi::getCategoryApi).distinct())

        repo.saveAll(products);
    }
}
