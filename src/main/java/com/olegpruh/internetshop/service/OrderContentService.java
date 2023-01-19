package com.olegpruh.internetshop.service;

import com.olegpruh.internetshop.model.OrderContent;

import java.util.List;

public interface OrderContentService {
    OrderContent save(OrderContent orderContent);

    List<OrderContent> findAllByOrderId(Long order_id);

    void deleteAllByOrder_Id(Long order_id);
}
