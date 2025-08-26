package org.example.javabackend2.Apifetch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiFetch {

    public static void main(String[] args){
        ApiFetch apiFetch = new ApiFetch();
        apiFetch.fetchApiAsString();
    }


//    public void saveProducts() throws IOException, InterruptedException {
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
//        List<Product> products = Arrays.asList(
//                mapper.readValue(response.body(), Product[].class)
//        );
//
//        repository.saveAll(products);
//    }





    public void fetchApiAsString() {


        try{
            URL url = new URL("https://fakestoreapi.com/products");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            //in.close();
        }catch (Exception e){
            System.out.println("ERROR exeption e" + e.getMessage());
        }
    }
}
