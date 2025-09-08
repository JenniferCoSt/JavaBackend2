package org.example.javabackend2.webbservice.models;

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
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
