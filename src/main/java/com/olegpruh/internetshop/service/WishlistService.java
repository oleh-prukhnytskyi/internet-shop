package com.olegpruh.internetshop.service;

import com.olegpruh.internetshop.model.Product;
import com.olegpruh.internetshop.model.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WishlistService {
    Page<Product> findAllByUserEmail(String user_email, Pageable pageable);

    List<Wishlist> findAllByUserEmail(String user_email);

    Wishlist save(String user_email, Long product_id);

    void deleteByUserEmailAndProductId(String user_email, Long product_id);

    List<Long> wishlistProductsId(String user_email);
}
