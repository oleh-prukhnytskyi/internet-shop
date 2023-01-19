package com.olegpruh.internetshop.repository;

import com.olegpruh.internetshop.model.Order;
import com.olegpruh.internetshop.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByOrderId(Long order_id);

    @Transactional
    void deleteByOrder_Id(Long order_id);
}
