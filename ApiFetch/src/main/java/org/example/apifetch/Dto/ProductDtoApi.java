package org.example.apifetch.Dto;


import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDtoApi {
    private Long id;
    private String title;
    private String description;
    private double price;
    @Embedded
    private RatingDtoApi rating;
    private String category;
    private String image;


}
