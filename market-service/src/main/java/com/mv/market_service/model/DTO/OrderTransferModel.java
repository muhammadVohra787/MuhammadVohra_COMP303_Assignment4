package com.mv.market_service.model.DTO;

public class OrderTransferModel {
    private String id;
    
    private String userId;  // Reference to the user who placed the order
    
    private String stockSymbol;
    
    private int quantity;
    
    private double price;
    
    private String status;  // e.g., "pending", "completed"

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderTransferModel [id=" + id + ", userId=" + userId + ", stockSymbol=" + stockSymbol + ", quantity="
				+ quantity + ", price=" + price + ", status=" + status + "]";
	}
	
	
}
