package org.example.apifetch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifetch.model.CategoryApi;
import org.example.apifetch.model.ProductApi;
import org.example.apifetch.repository.ApiCategoryRepo;
import org.example.apifetch.repository.ApiProductRepo;
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

    private final ApiProductRepo pruductRepo;
    private final ApiCategoryRepo catRepo;

    public ApiController(ApiProductRepo pruductRepo, ApiCategoryRepo catRepo) {
        this.pruductRepo = pruductRepo;
        this.catRepo = catRepo;
    }

//    public void addProduct(ProductApi product) {
//        pruductRepo.save(product);
//    }

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

        products.forEach(System.out::println);

        System.out.println("Start!");

        products.stream().map(ProductApi::getCategory).forEach(System.out::println);
        System.out.println("\n\n");
        products.stream().map(ProductApi::getCategory).distinct().forEach(System.out::println);

        List<CategoryApi> categories = products.stream().map(ProductApi::getCategory).distinct().toList();

        catRepo.saveAll(categories);



//        List<CategoryApi> categorys = new ArrayList<>();
//        System.out.println("1");
//        categorys.add((CategoryApi) products.stream().map(ProductApi::getCategory).distinct());
//        System.out.println("2");
//        categorys.forEach(System.out::println);
//        System.out.println("3");
//        repo.saveAll(categorys)



        System.out.println("Stop!");


//        repo.saveAll(products.stream().map(ProductApi::getCategoryApi).distinct())

        pruductRepo.saveAll(products);
    }
}
