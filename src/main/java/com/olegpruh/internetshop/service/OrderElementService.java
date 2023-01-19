package com.olegpruh.internetshop.service;

import com.olegpruh.internetshop.model.OrderElement;

import java.util.List;

public interface OrderElementService {
    List<OrderElement> getOrderElementsByUser(String user_email);

    OrderElement getOrderElementById(Long id);
}
