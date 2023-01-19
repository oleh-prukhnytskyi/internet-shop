package com.olegpruh.internetshop.service;

import com.olegpruh.internetshop.model.Cart;
import com.olegpruh.internetshop.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    Cart save(String user_email, Long product_id);

    Cart findByUserEmail(String user_email);

    void deleteCartByUserEmailAndProductId(String user_email, Long product_id);

    void remove(String user_email, Long product_id);

    List<Cart> findAllByUserEmail(String user_email);

    BigDecimal getTotalPrice(List<Cart> carts);

    void deleteAllByUserEmail(String user_email);
}
