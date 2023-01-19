package com.olegpruh.internetshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
    private int rating;
    private String name;

    public Review(String text, User user, Product product, int rating, String name) {
        this.text = text;
        this.user = user;
        this.product = product;
        this.rating = rating;
        this.name = name;
    }
}
