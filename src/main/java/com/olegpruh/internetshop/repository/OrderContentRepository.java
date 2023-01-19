package com.olegpruh.internetshop.repository;

import com.olegpruh.internetshop.model.OrderContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderContentRepository extends JpaRepository<OrderContent, Long> {
    List<OrderContent> findAllByOrderId(Long order_id);

    @Transactional
    void deleteAllByOrder_Id(Long order_id);
}
