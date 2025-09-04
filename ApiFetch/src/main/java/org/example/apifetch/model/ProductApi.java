package org.example.apifetch.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = "Product")
public class ProductApi {


//    @Id
//    public int id;      //måste använda Long?
//    public String title;
//    public double price;
//    @Column (length = 2000)
//    public String description;
//    public String category;
//    public String image;
//    @Embedded
//    public RatingApi rating;





    @Id
    private Long id;
    private String title;
    @Column (length = 2000)
    private String description;
    private double price;


    @Embedded
    private RatingApi rating;


    @Getter
    @ManyToOne
    @JoinColumn(name = "Category", referencedColumnName = "type")
    private CategoryApi category;


    //TODO fix
    //@OneToMany(mappedBy = "product")
    //private List<Order> orders;

    private String image;

}
