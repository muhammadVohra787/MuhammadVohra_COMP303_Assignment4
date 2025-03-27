# Order Management System

## Overview
This is a **Java 21** microservices-based order management system built with **Spring Boot** and **Maven**. The system consists of three backend microservices:

1. **Order Service** – Handles user registration, login, and order processing.
2. **Transaction Service** – Manages order-related transactions.
3. **Market Service** – Simulates stock market order placements.

## Microservices

### 1. Order Service (`order-service`)
Handles user registration, login, and order processing.

#### Endpoints:
- `GET /` – Home page.
- `GET /register` – Show registration form.
- `POST /register` – Register a new user.
- `GET /login` – Show login form.
- `POST /login` – Authenticate user.
- `GET /orderForm?userId={id}` – Display order form.
- `POST /placeOrder` – Place an order and communicate with Transaction & Market services.
- `GET /userOrders/{userId}` – Get user's orders.
- `GET /sellOrder?orderId={id}` – Sell an order.

#### Service Integration:
- Calls **Transaction Service** (`http://localhost:8082/transaction/createTransaction`)
- Calls **Market Service** (`http://localhost:8083/market/createMarketOrder`)

---

### 2. Transaction Service (`transaction-service`)
Handles order transactions.

#### Endpoints:
- `POST /transaction/createTransaction` – Create a transaction.
- `GET /transaction/{id}` – Fetch transaction details.
- `POST /transaction/sellTransaction` – Mark a transaction as sold.

---

### 3. Market Service (`market-service`)
Simulates stock market orders.

#### Endpoints:
- `POST /market/createMarketOrder` – Place a stock order in the market.
- `POST /market/sellMarketOrder` – Sell stock in the market.

---

## Setup Instructions

### Prerequisites
- Java 21
- Maven
- Spring Boot
- MongoDB

## License
This project is open-source and available under the **MIT License**.
