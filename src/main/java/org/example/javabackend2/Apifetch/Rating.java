package org.example.javabackend2.Apifetch;

import jakarta.persistence.Embeddable;

@Embeddable
public class Rating {
    public double rate;
    public int count;
}
