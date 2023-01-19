package com.olegpruh.internetshop.service;

import com.olegpruh.internetshop.model.Transaction;

public interface TransactionService {
    Transaction save(Transaction transaction);

    Transaction findByOrderId(Long order_id);

    void deleteByOrder_Id(Long order_id);
}
