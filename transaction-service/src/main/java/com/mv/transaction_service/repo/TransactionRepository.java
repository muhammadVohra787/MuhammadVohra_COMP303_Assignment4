package com.mv.transaction_service.repo;

import com.mv.transaction_service.model.Transaction;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
	Optional<Transaction> findByOrderId(String orderId);
}
