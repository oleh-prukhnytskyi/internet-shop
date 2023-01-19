package com.olegpruh.internetshop.repository;

import com.olegpruh.internetshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUserEmail(String user_email);

    List<Order> findAllByUserEmail(String user_email);

    @Transactional
    void deleteById(Long id);
}
