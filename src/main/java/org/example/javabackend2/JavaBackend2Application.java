package org.example.javabackend2;

import org.example.javabackend2.models.Role;
import org.example.javabackend2.models.User;
import org.example.javabackend2.repos.RoleRepository;
import org.example.javabackend2.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JavaBackend2Application {

    public static void main(String[] args) {
        SpringApplication.run(JavaBackend2Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {

            Role role = new Role();
            role.setType("admin");
            role = roleRepository.save(role);

            userRepository.save(new User("Sigrun", "olafsdottir@4ever.se", "HemligtLösenord", role));

        };
    }
}
