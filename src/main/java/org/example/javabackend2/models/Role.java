package org.example.javabackend2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role(String type) {
        this.type = type;
    }
}
