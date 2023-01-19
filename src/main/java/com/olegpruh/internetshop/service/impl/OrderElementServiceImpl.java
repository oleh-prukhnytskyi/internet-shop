package com.olegpruh.internetshop.service.impl;

import com.olegpruh.internetshop.model.Order;
import com.olegpruh.internetshop.model.OrderContent;
import com.olegpruh.internetshop.model.OrderElement;
import com.olegpruh.internetshop.service.OrderContentService;
import com.olegpruh.internetshop.service.OrderElementService;
import com.olegpruh.internetshop.service.OrderService;
import com.olegpruh.internetshop.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderElementServiceImpl implements OrderElementService {
    private final OrderService orderService;
    private final OrderContentService orderContentService;
    private final TransactionService transactionService;

    public OrderElementServiceImpl(OrderService orderService, OrderContentService orderContentService,
                                   TransactionService transactionService) {
        this.orderService = orderService;
        this.orderContentService = orderContentService;
        this.transactionService = transactionService;
    }

    @Override
    public List<OrderElement> getOrderElementsByUser(String user_email) {
        List<OrderElement> orderElements = new ArrayList<>();
        for (Order order : orderService.findAllByUserEmail(user_email)) {
            orderElements.add(new OrderElement(order, orderContentService.findAllByOrderId(order.getId()),
                    transactionService.findByOrderId(order.getId())));
        }
        return orderElements;
    }

    @Override
    public OrderElement getOrderElementById(Long id) {
        Order order = orderService.getById(id);
        return new OrderElement(order, orderContentService.findAllByOrderId(order.getId()),
                transactionService.findByOrderId(order.getId()));
    }
}
