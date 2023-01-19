package com.olegpruh.internetshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderElement {
    private Order order;
    private List<OrderContent> orderContent;
    private Transaction transaction;
}
