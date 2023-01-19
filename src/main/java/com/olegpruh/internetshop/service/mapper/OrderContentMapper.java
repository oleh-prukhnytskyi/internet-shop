package com.olegpruh.internetshop.service.mapper;

import com.olegpruh.internetshop.model.Cart;
import com.olegpruh.internetshop.model.Order;
import com.olegpruh.internetshop.model.OrderContent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OrderContentMapper {
    public OrderContent cartToOrderContent(Cart cart, Order order) {
        OrderContent orderContent = new OrderContent();
        orderContent.setProduct(cart.getProduct());
        orderContent.setPricePer(cart.getPricePer());
        orderContent.setShipDate(LocalDate.now().plusDays(3)); // TODO: 03/01/2023 plus days from DeliverOption
        orderContent.setOrder(order);
        orderContent.setQuantity(cart.getQuantity());
        return orderContent;
    }
}
