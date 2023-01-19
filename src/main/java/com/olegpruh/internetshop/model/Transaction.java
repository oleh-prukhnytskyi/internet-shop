package com.olegpruh.internetshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionId;
    @OneToOne
    private Order order;
    private BigDecimal amount;
    private BigDecimal fee;
    private String method;
    private String status;
    private LocalDate transactionDate;
    private int cardNumber;
    private String invoice;
}
