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
@Table (name = "product")
public class Product {
    @Id
    private Long id;
    private String title;
    @Column (length = 2000)
    private String description;
    private double price;
    @Embedded
    private Rating rating;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    //TODO fix
    @OneToMany(mappedBy = "product")
    private List<Order> orders;

    private String image;
}
