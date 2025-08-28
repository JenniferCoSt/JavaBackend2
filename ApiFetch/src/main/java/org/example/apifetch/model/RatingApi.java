package org.example.apifetch.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Embeddable
public class RatingApi {
    public double rate;
    public int count;
}
