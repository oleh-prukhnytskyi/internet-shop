package com.olegpruh.internetshop.repository;

import com.olegpruh.internetshop.model.Product;
import com.olegpruh.internetshop.model.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Page<Wishlist> findAllByUserEmail(String user_email, Pageable pageable);

    List<Wishlist> findAllByUserEmail(String user_email);

    Wishlist findByUserEmailAndProductId(String user_email, Long product_id);

    @Transactional
    void deleteByUserEmailAndProductId(String user_email, Long product_id);
}
