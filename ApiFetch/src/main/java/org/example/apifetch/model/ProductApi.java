package org.example.apifetch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table (name = "Product")
public class ProductApi {

    @Id
    private Long id;
    private String title;
    private String description;
    private double price;
    @Embedded
    private RatingApi rating;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryApi category;
    //TODO fix
    //@OneToMany(mappedBy = "product")
    //private List<Order> orders;

    private String image;

    public CategoryApi getCategory() {
        return category;
    }
}
