package com.mv.market_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mv.market_service.model.DTO.OrderTransferModel;

@RestController
@RequestMapping("/market") 
public class MarketController {
	
	@PostMapping("/createMarketOrder")
	public Boolean createMarketOrder(@RequestBody OrderTransferModel order) {
	    System.out.println("Received order: " + order);

	    // Check for missing attributes
	    StringBuilder missingAttributes = new StringBuilder();
	    
	    if (order.getUserId() == null) missingAttributes.append(" userId");
	    if (order.getStockSymbol() == null) missingAttributes.append(" stockSymbol");
	    if (order.getQuantity() <= 0) missingAttributes.append(" quantity");
	    if (order.getPrice() <= 0) missingAttributes.append(" price");
	    if (order.getStatus() == null) missingAttributes.append(" status");

	    if (missingAttributes.length() > 0) {
	        System.out.println("Missing attribute(s):" + missingAttributes);
	        throw new RuntimeException("Market Stock creation failed.");
	    }

	    // If everything is fine, log success
	    System.out.println("Stock order placed successfully!");
	    System.out.println("User " + order.getUserId() + " placed an order for " + order.getQuantity() + 
	        " shares of " + order.getStockSymbol() + " at $" + order.getPrice() + " per share.");

	    return true;
	}
	
	@PostMapping("/sellMarketOrder")
	public Boolean sellMarketOrder(@RequestBody OrderTransferModel order) {
		System.out.println("Received order: " + order);

	    // Check for missing attributes
	    StringBuilder missingAttributes = new StringBuilder();
	    
	    if (order.getUserId() == null) missingAttributes.append(" userId");
	    if (order.getStockSymbol() == null) missingAttributes.append(" stockSymbol");
	    if (order.getQuantity() <= 0) missingAttributes.append(" quantity");
	    if (order.getPrice() <= 0) missingAttributes.append(" price");
	    if (order.getStatus() == null) missingAttributes.append(" status");

	    if (missingAttributes.length() > 0) {
	        System.out.println("Missing attribute(s):" + missingAttributes);
	        throw new RuntimeException("Market Stock selling failed.");
	    }

	    // If everything is fine, log success
	    System.out.println("Stock order sold successfully!");
	    System.out.println("User " + order.getUserId() + " placed an order for selling" + order.getQuantity() + 
	        " shares of " + order.getStockSymbol() + " at $" + order.getPrice() + " per share.");

	    return true;
	}


}
