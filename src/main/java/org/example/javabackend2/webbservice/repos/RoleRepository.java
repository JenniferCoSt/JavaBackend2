package org.example.javabackend2.webbservice.repos;

import org.example.javabackend2.webbservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByType(String name);
}
