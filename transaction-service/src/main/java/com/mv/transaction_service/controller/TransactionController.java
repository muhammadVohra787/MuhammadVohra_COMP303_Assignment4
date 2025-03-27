package com.mv.transaction_service.controller;

import com.mv.transaction_service.model.Transaction;
import com.mv.transaction_service.model.DTO.OrderTransferModel;
import com.mv.transaction_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Create a new transaction
    @PostMapping("/createTransaction")
    public boolean createTransaction(@RequestBody OrderTransferModel order) {
        System.out.println("Request In");
        
        Transaction transaction = new Transaction(order);
        Transaction savedTransaction = transactionService.createTransaction(transaction);

        if (savedTransaction == null) {
            throw new RuntimeException("Transaction creation failed.");
        }

        System.out.println(savedTransaction.toString());
        return true; // Transaction is created successfully
    }

    // Get a specific transaction by ID
    @GetMapping("/transaction/{id}")
    public boolean getTransaction(@PathVariable String id) {
        Transaction transaction = transactionService.getTransactionById(id);
        
        if (transaction == null) {
            throw new RuntimeException("Transaction not found.");
        }

        return true; // Transaction is found
    }

    @PostMapping("/sellTransaction")
    public boolean sellTransaction(@RequestBody OrderTransferModel order) {
        Transaction transaction = transactionService.getTransactionByOrderId(order.getId());
        
        if (transaction == null) {
            throw new RuntimeException("Transaction not found for selling.");
        }

        transaction.setTransactionType("SELL");
        return true; // Successfully marked as SELL
    }
}
