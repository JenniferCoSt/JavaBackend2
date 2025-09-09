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

        };
    }

}
