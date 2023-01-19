package com.olegpruh.internetshop.service;

import com.olegpruh.internetshop.model.Order;
import com.olegpruh.internetshop.model.dto.CheckoutDto;

import java.util.List;

public interface OrderService {
    Order save(Order order);

    Order generateOrder(String user_email);

    Order findByUserEmail(String user_email);

    List<Order> findAllByUserEmail(String user_email);

    Order getById(Long id);

    void deleteById(Long id);
}
