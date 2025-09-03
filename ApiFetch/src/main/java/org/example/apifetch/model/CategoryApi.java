package org.example.apifetch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class CategoryApi {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    @OneToMany(mappedBy = "category")
    private List<ProductApi> products;

    public CategoryApi(String type) {
        this.type = type;
    }
}
