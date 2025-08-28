package org.example.javabackend2.Apifetch;

import jakarta.persistence.*;

@Entity
@Table (name = "Product")
public class ProductApi {

    @Id
    public int id;      //måste använda Long?
    public String title;
    public double price;
    @Column (length = 2000)
    public String description;
    public String category;
    public String image;
    @Embedded
    public Rating rating;
}
