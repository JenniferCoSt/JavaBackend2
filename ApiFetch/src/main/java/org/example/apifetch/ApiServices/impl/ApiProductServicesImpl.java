package org.example.apifetch.ApiServices.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifetch.ApiServices.ApiCategoryServices;
import org.example.apifetch.ApiServices.ApiProductServices;
import org.example.apifetch.Dto.CategoryDtoApi;
import org.example.apifetch.Dto.ProductDtoApi;
import org.example.apifetch.model.CategoryApi;
import org.example.apifetch.model.ProductApi;
import org.example.apifetch.repository.ApiCategoryRepo;
import org.example.apifetch.repository.ApiProductRepo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Service
public class ApiProductServicesImpl implements ApiProductServices {

    private final ApiCategoryRepo catRepo;
    private final ApiProductRepo prodRepo;

    private final ApiCategoryServices apiCategoryServices;

    public ApiProductServicesImpl(ApiCategoryRepo catRepo, ApiProductRepo prodRepo) {
        this.catRepo = catRepo;
        this.prodRepo = prodRepo;
        this.apiCategoryServices = ApiCategoryServices;
    }


    public void saveProductsFromApi(){

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://fakestoreapi.com/products"))
                    .GET()
                    .build();


            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            List<ProductDtoApi> productsDtos = Arrays.asList(mapper.readValue(response.body(), ProductDtoApi[].class));


            productsDtos.forEach(System.out::println);

            System.out.println("\nStart!\n");

            productsDtos.stream().map(ProductDtoApi::getCategory).forEach(System.out::println);
            System.out.println("\n\n");
            productsDtos.stream().map(ProductDtoApi::getCategory).distinct().forEach(System.out::println);

            List<String> newCategorys = productsDtos.stream().map(ProductDtoApi::getCategory).distinct().toList();

            newCategorys.forEach(c -> catServices.addCategory(CategoryDtoApi.builder().type(c).build()));





        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }

//    public void saveProducts() throws IOException, InterruptedException { //kör try with res
//        HttpClient client = HttpClient.newHttpClient();
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://fakestoreapi.com/products"))
//                .GET()
//                .build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        ObjectMapper mapper = new ObjectMapper();
//        List<ProductDtoApi> productsDto = Arrays.asList(
//                mapper.readValue(response.body(), ProductDtoApi[].class)
//        );
//
//        productsDto.forEach(System.out::println);
//
////        System.out.println("Start!");
////
////        products.stream().map(ProductApi::getCategory).forEach(System.out::println);
////        System.out.println("\n\n");
////        products.stream().map(ProductApi::getCategory).distinct().forEach(System.out::println);
////
////        List<CategoryApi> categories = products.stream().map(ProductApi::getCategory).distinct().toList();
////
////        catRepo.saveAll(categories);
//
//
////        List<CategoryApi> categorys = new ArrayList<>();
////        System.out.println("1");
////        categorys.add((CategoryApi) products.stream().map(ProductApi::getCategory).distinct());
////        System.out.println("2");
////        categorys.forEach(System.out::println);
////        System.out.println("3");
////        repo.saveAll(categorys)
//
//
//        System.out.println("Stop!");
//
//
////        repo.saveAll(products.stream().map(ProductApi::getCategoryApi).distinct())
//
////        pruductRepo.saveAll(products);
//    }
















}


/*bör ha full api save eller nått
tar in prudukt dto
gör lista med categorier
*** Här behövs skapas kategori entiteter i kategori service
Metod som tittar efter kategori (null eller kat)
metod som lägger till kat

***sparar ner kategorier
fortsättning i prudukt service
prudukt entiterer skapas i prosessen hämntas kategori entiteter från db och läggs till i prudukt objekt
prudukter sparas ned

 */
