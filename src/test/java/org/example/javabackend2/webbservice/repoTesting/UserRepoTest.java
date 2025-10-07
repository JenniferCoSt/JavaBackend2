package org.example.javabackend2.webbservice.repoTesting;


import org.example.javabackend2.webbservice.models.Role;
import org.example.javabackend2.webbservice.models.User;
import org.example.javabackend2.webbservice.repos.UserRepository;
import org.example.javabackend2.webbservice.repos.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY,
        connection = EmbeddedDatabaseConnection.H2)
class UserRepoTest {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Test
    void save_and_load_user_repository() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        Role role = roleRepo.save(new Role("USER"));

        User u = new User("Bertil", "olafsson@2be.se", encoder.encode("qwe"), role);
        userRepo.save(u);

        User found = userRepo.findByEmail("olafsson@2be.se").orElseThrow();
        assertThat(encoder.matches("qwe", found.getPassword())).isTrue();
    }

    @Test
    void save_and_delete_user_repository() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        Role role = roleRepo.save(new Role("USER"));

        User u = new User("hertil", "olafsson@4be.se", encoder.encode("qwe"), role);
        userRepo.save(u);

        User found = userRepo.findByEmail("olafsson@4be.se").orElseThrow();
        assertThat(found.getId()).isNotNull();
        userRepo.delete(found);
        assertThat(userRepo.findByEmail("olafsson@4be.se").isEmpty()).isTrue();
    }
}
