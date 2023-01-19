package com.olegpruh.internetshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private BigDecimal total;
    private BigDecimal shipping;
    private LocalDate orderDate;
    private String name;
    private String email;
    @OneToOne
    private Address address;
    private String phone;
    private int trackStatus;
}
