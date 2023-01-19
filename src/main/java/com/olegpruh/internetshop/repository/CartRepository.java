package com.olegpruh.internetshop.repository;

import com.olegpruh.internetshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserEmail(String user_email);

    List<Cart> findAllByUserEmail(String user_email);

    Cart findByUserEmailAndProductId(String user_email, Long product_id);

    @Transactional
    void deleteCartByUserEmailAndProductId(String user_email, Long product_id);

    @Modifying
    @Transactional
    @Query("UPDATE Cart c SET c.quantity = ?1 WHERE c.id = ?2")
    void update(int quantity, Long id);

    @Transactional
    void deleteAllByUserEmail(String user_email);
}
