package com.mv.order_service.service;

import com.mv.order_service.model.Order;
import com.mv.order_service.repo.OrderRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
    
    public Boolean deleteOrder(Order order) {
    	orderRepository.deleteById(order.getId());
    	
    	return true;
    }
    
    public List<Order> getUserOrders(String userId) {
        return orderRepository.findByUserId(userId);
    }
}

