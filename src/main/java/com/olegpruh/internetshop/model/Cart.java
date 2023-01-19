package com.olegpruh.internetshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    private int quantity;
    private BigDecimal totalPrice;
    private BigDecimal pricePer;

    public Cart(User user, Product product, int quantity, BigDecimal pricePer) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.pricePer = pricePer;
    }

    public BigDecimal getTotalPrice() {
        totalPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        return totalPrice;
    }
}
