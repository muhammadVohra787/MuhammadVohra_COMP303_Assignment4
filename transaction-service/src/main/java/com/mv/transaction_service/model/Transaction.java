package com.mv.transaction_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mv.transaction_service.model.DTO.OrderTransferModel;

@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;
    
    private String userId;  // Reference to the user who placed the order
    
    private String stockSymbol;
    
    private int quantity;
    
    private double price;
    
   
    private String orderId;
    
    private String transactionType; //"BUY" "SELL"
    
    private double totalCost;
    
    public Transaction() {}

    public Transaction(OrderTransferModel order) {
    	this.userId = order.getId();
    	this.stockSymbol = order.getStockSymbol();
    	this.quantity = order.getQuantity();
    	this.price = order.getPrice();
    	this.transactionType = order.getStatus().equals("pending") ? "BUY" : "SELL";
    	this.totalCost = order.getPrice() * order.getQuantity();
    	this.orderId = order.getId();
    	
    }
    
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", userId=" + userId + ", stockSymbol=" + stockSymbol + ", quantity="
				+ quantity + ", price=" + price + ", orderId=" + orderId + ", transactionType=" + transactionType
				+ ", totalCost=" + totalCost + "]";
	}
    
}
