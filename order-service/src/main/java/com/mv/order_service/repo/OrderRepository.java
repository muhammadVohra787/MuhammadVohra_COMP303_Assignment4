package com.mv.order_service.repo;


import com.mv.order_service.model.Order;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
	List<Order> findByUserId(String userId);
}
