package org.example.apifetch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifetch.model.ProductApi;
import org.example.apifetch.repository.ApiRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

        repo.saveAll(products);
    }
}
