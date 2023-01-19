package com.olegpruh.internetshop.service.impl;

import com.olegpruh.internetshop.model.OrderContent;
import com.olegpruh.internetshop.repository.OrderContentRepository;
import com.olegpruh.internetshop.service.OrderContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderContentServiceImpl implements OrderContentService {
    private final OrderContentRepository orderContentRepository;

    @Autowired
    public OrderContentServiceImpl(OrderContentRepository orderContentRepository) {
        this.orderContentRepository = orderContentRepository;
    }

    @Override
    public OrderContent save(OrderContent orderContent) {
        return orderContentRepository.save(orderContent);
    }

    @Override
    public List<OrderContent> findAllByOrderId(Long order_id) {
        return orderContentRepository.findAllByOrderId(order_id);
    }

    @Override
    public void deleteAllByOrder_Id(Long order_id) {
        orderContentRepository.deleteAllByOrder_Id(order_id);
    }
}
