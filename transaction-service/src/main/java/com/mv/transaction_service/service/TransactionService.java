package com.mv.transaction_service.service;

import com.mv.transaction_service.model.Transaction;
import com.mv.transaction_service.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(String id) {
        return transactionRepository.findById(id).orElse(null);
    }
    
    public Transaction getTransactionByOrderId(String orderId) {
    	return transactionRepository.findByOrderId(orderId).orElse(null);
    }
    
    // Other business logic related to transactions can go here
}
