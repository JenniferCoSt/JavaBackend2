package org.example.apifetch.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Id;

public class simpelPruductApi {

    @Id
    public int id;      //måste använda Long?
    public String title;
    public double price;
    @Column(length = 2000)
    public String description;
    public String category;
    public String image;
    @Embedded
    public RatingApi rating;



}
