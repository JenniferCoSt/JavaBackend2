package org.example.javabackend2.webbservice.repos;

import org.example.javabackend2.webbservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
