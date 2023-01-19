package com.olegpruh.internetshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private BigDecimal price;
    @OneToMany
    private List<ProductImage> images;
    @Column(columnDefinition="TEXT")
    private String description;

    public Product(String title, BigDecimal price, List<ProductImage> images, String description) {
        this.title = title;
        this.price = price;
        this.images = images;
        this.description = description;
    }
}