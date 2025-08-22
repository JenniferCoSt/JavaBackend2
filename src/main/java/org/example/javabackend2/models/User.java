package org.example.javabackend2.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Getter @Setter
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
