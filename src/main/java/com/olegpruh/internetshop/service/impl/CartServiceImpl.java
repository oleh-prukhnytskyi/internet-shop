package com.olegpruh.internetshop.service.impl;

import com.olegpruh.internetshop.model.Cart;
import com.olegpruh.internetshop.model.Product;
import com.olegpruh.internetshop.model.User;
import com.olegpruh.internetshop.repository.CartRepository;
import com.olegpruh.internetshop.service.CartService;
import com.olegpruh.internetshop.service.ProductService;
import com.olegpruh.internetshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserService userService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public Cart save(String user_email, Long product_id) {
        User user = userService.findByEmail(user_email).get();
        Product product = productService.get(product_id);
        Cart cart = new Cart(user, product, 1, product.getPrice());
        Cart cartByUserEmail = cartRepository.findByUserEmailAndProductId(user_email, product_id);
        if (cartByUserEmail != null) {
            cartRepository.update(cartByUserEmail.getQuantity() + 1,
                    cartByUserEmail.getId());
            return cartRepository.getById(cartByUserEmail.getId());
        } else {
            return cartRepository.save(cart);
        }
    }

    @Override
    public Cart findByUserEmail(String user_email) {
        return cartRepository.findByUserEmail(user_email);
    }

    @Override
    public void deleteCartByUserEmailAndProductId(String user_email, Long product_id) {
        cartRepository.deleteCartByUserEmailAndProductId(user_email, product_id);
    }

    @Override
    public void remove(String user_email, Long product_id) {
        Cart cartByUserEmail = cartRepository.findByUserEmailAndProductId(user_email, product_id);
        if (cartByUserEmail.getQuantity() - 1 >= 1) {
            cartRepository.update(cartByUserEmail.getQuantity() - 1,
                    cartByUserEmail.getId());
        }
    }

    @Override
    public List<Cart> findAllByUserEmail(String user_email) {
        return cartRepository.findAllByUserEmail(user_email);
    }

    @Override
    public BigDecimal getTotalPrice(List<Cart> carts) {
        return carts.stream()
                .map(x -> x.getPricePer().multiply(BigDecimal.valueOf(x.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void deleteAllByUserEmail(String user_email) {
        cartRepository.deleteAllByUserEmail(user_email);
    }
}
