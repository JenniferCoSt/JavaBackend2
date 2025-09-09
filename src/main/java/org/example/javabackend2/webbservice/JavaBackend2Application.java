package org.example.javabackend2.webbservice;

import org.example.javabackend2.webbservice.models.*;
import org.example.javabackend2.webbservice.repos.CategoryRepository;
import org.example.javabackend2.webbservice.repos.ProductRepository;
import org.example.javabackend2.webbservice.repos.RoleRepository;
import org.example.javabackend2.webbservice.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JavaBackend2Application {

    public static void main(String[] args) {
        SpringApplication.run(org.example.javabackend2.webbservice.JavaBackend2Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, RoleRepository roleRepository,
                                               ProductRepository productRepository, CategoryRepository categoryRepository,
                                               PasswordEncoder passwordEncoder) {
        return args -> {

            Role role1 = new Role();
            if (roleRepository.findByType("admin").isEmpty()) {
                role1.setType("admin");
                roleRepository.save(role1);
            }

            if (userRepository.findByEmail("olafsdottir@4ever.se").isEmpty()) {
                userRepository.save(new User("Sigrun", "olafsdottir@4ever.se", passwordEncoder.encode("asd"), role1));
            }

            Role role2 = new Role();
            if (roleRepository.findByType("user").isEmpty()) {
                role2.setType("user");
                roleRepository.save(role2);
            }

            if (userRepository.findByEmail("Lonneberga@4ever.se").isEmpty()) {
                userRepository.save(new User("Emil", "Lonneberga@4ever.se", passwordEncoder.encode("1234"), role2));
            }
//
//            Category cat1 = new Category();
//            cat1.setType("electronics");
//            cat1 = categoryRepository.save(cat1);
//
//            Category cat2 = new Category();
//            cat2.setType("jewelery");
//            cat2 = categoryRepository.save(cat2);
//
//            Category cat3 = new Category();
//            cat3.setType("men's clothing");
//            cat3 = categoryRepository.save(cat3);
//
//            Category cat4 = new Category();
//            cat4.setType("women's clothing");
//            cat4 = categoryRepository.save(cat4);
//
//
//            Product p1 = new Product();
//            p1.setId(10001L);
//            p1.setTitle("Wireless Headphones");
//            p1.setDescription("Over-ear Bluetooth headphones with 30h battery.");
//            p1.setPrice(79.99);
//            p1.setRating(new Rating(4.2, 314));
//            p1.setCategory(cat1);
//            p1.setImage("https://picsum.photos/seed/phones1/600/400");
//            productRepository.save(p1);
//
//            Product p2 = new Product();
//            p2.setId(10002L);
//            p2.setTitle("27\" 4K Monitor");
//            p2.setDescription("27-inch 4K IPS, HDR10, 60Hz.");
//            p2.setPrice(269.00);
//            p2.setRating(new Rating(4.5, 128));
//            p2.setCategory(cat1);
//            p2.setImage("https://picsum.photos/seed/monitor4k/600/400");
//            productRepository.save(p2);
//
//            Product p3 = new Product();
//            p3.setId(10003L);
//            p3.setTitle("Silver Necklace");
//            p3.setDescription("Minimal necklace in sterling silver.");
//            p3.setPrice(39.50);
//            p3.setRating(new Rating(4.0, 88));
//            p3.setCategory(cat2);
//            p3.setImage("https://picsum.photos/seed/necklace/600/400");
//            productRepository.save(p3);
//
//            Product p4 = new Product();
//            p4.setId(10004L);
//            p4.setTitle("Men’s Cotton T-Shirt");
//            p4.setDescription("Regular fit, 100% cotton.");
//            p4.setPrice(12.95);
//            p4.setRating(new Rating(3.9, 210));
//            p4.setCategory(cat3);
//            p4.setImage("https://picsum.photos/seed/mtee/600/400");
//            productRepository.save(p4);
//
//            Product p5 = new Product();
//            p5.setId(10005L);
//            p5.setTitle("Women’s Denim Jacket");
//            p5.setDescription("Classic denim jacket, light wash.");
//            p5.setPrice(59.90);
//            p5.setRating(new Rating(4.6, 177));
//            p5.setCategory(cat4);
//            p5.setImage("https://picsum.photos/seed/wdenim/600/400");
//            productRepository.save(p5);
//
//            Product p6 = new Product();
//            p6.setId(10006L);
//            p6.setTitle("USB-C Charger 65W");
//            p6.setDescription("GaN fast charger with 2 ports.");
//            p6.setPrice(34.99);
//            p6.setRating(new Rating(4.7, 402));
//            p6.setCategory(cat1);
//            p6.setImage("https://picsum.photos/seed/charger65/600/400");
//            productRepository.save(p6);
//
//
        };
    }

}
