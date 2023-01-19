package com.olegpruh.internetshop.service.impl;

import com.olegpruh.internetshop.model.Product;
import com.olegpruh.internetshop.model.User;
import com.olegpruh.internetshop.model.Wishlist;
import com.olegpruh.internetshop.repository.WishlistRepository;
import com.olegpruh.internetshop.service.ProductService;
import com.olegpruh.internetshop.service.UserService;
import com.olegpruh.internetshop.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public WishlistServiceImpl(WishlistRepository wishlistRepository,
                               UserService userService, ProductService productService) {
        this.wishlistRepository = wishlistRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public Page<Product> findAllByUserEmail(String user_email, Pageable pageable) {
        Page<Wishlist> wishlists = wishlistRepository.findAllByUserEmail(user_email, pageable);
        List<Product> products = wishlists.stream().map(Wishlist::getProduct).collect(Collectors.toList());
        return new PageImpl<>(products, wishlists.getPageable(), wishlists.getTotalElements());
    }

    @Override
    public List<Wishlist> findAllByUserEmail(String user_email) {
        return wishlistRepository.findAllByUserEmail(user_email);
    }

    @Override
    public Wishlist save(String user_email, Long product_id) {
        Wishlist wishlist = wishlistRepository.findByUserEmailAndProductId(user_email, product_id);
        if (wishlist != null) return wishlist;
        User user = userService.findByEmail(user_email).get();
        return wishlistRepository.save(new Wishlist(user, productService.get(product_id)));
    }

    @Override
    public void deleteByUserEmailAndProductId(String user_email, Long product_id) {
        wishlistRepository.deleteByUserEmailAndProductId(user_email, product_id);
    }

    @Override
    public List<Long> wishlistProductsId(String user_email) {
        return wishlistRepository.findAllByUserEmail(user_email)
                .stream()
                .map(w -> w.getProduct().getId())
                .collect(Collectors.toList());
    }

}
