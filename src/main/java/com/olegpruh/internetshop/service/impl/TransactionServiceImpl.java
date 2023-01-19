package com.olegpruh.internetshop.service.impl;

import com.olegpruh.internetshop.model.Transaction;
import com.olegpruh.internetshop.repository.TransactionRepository;
import com.olegpruh.internetshop.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction findByOrderId(Long order_id) {
        return transactionRepository.findByOrderId(order_id);
    }

    @Override
    public void deleteByOrder_Id(Long order_id) {
        transactionRepository.deleteByOrder_Id(order_id);
    }
}
