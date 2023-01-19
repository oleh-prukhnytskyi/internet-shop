package com.olegpruh.internetshop.service.impl;

import com.olegpruh.internetshop.model.Customer;
import com.olegpruh.internetshop.model.Order;
import com.olegpruh.internetshop.repository.OrderRepository;
import com.olegpruh.internetshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final CustomerService customerService;
    private final UserService userService;
    private final OrderContentService orderContentService;
    private final TransactionService transactionService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CartService cartService,
                            CustomerService customerService, UserService userService, OrderContentService orderContentService, TransactionService transactionService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.customerService = customerService;
        this.userService = userService;
        this.orderContentService = orderContentService;
        this.transactionService = transactionService;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order generateOrder(String user_email) {
        Customer customer = customerService.findByUserEmail(user_email);
        Order order = new Order();
        order.setTotal(cartService.getTotalPrice(cartService.findAllByUserEmail(user_email)));
        order.setShipping(customer.getDeliveryOption().getPrice());
        order.setOrderDate(LocalDate.now());
        order.setUser(userService.findByEmail(user_email).get());
        order.setName(String.format("%1$s %2$s", customer.getFirstName(), customer.getLastName()));
        order.setEmail(customer.getEmail());
        order.setAddress(customer.getAddress());
        order.setPhone(customer.getPhone());
        order.setTrackStatus(0);
        return order;
    }

    @Override
    public Order findByUserEmail(String user_email) {
        return orderRepository.findByUserEmail(user_email);
    }

    @Override
    public List<Order> findAllByUserEmail(String user_email) {
        return orderRepository.findAllByUserEmail(user_email);
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        orderContentService.deleteAllByOrder_Id(id);
        transactionService.deleteByOrder_Id(id);
        orderRepository.deleteById(id);
    }
}
