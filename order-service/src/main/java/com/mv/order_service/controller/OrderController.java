package com.mv.order_service.controller;

import com.mv.order_service.model.Order;
import com.mv.order_service.model.User;
import com.mv.order_service.service.OrderService;
import com.mv.order_service.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String getIndex() {
    	return "index";
    }
    
    // Registration Form
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam Long balance,
                               Model model) {
        try {
            Optional <User> existingUser = userService.findByUsername(username);
        	if (existingUser != null && existingUser.isPresent())
        	{
                model.addAttribute("errorMessage", "Username already exists!");
                return "register";
            }

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setBalance(balance);
            userService.registerUser(newUser);

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Registration failed: " + e.getMessage());
            return "register";
        }

        return "redirect:/login";
    }

    // Login Form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Login Logic (Basic Authentication check)
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userService.findByUsername(username).orElse(null);
        try {

            if (user == null || !user.getPassword().equals(password)) {
                model.addAttribute("errorMessage", "Invalid username or password!");
                return "login";
            }

            model.addAttribute("user", user);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Login failed: " + e.getMessage());
            return "login";
        }

        return "redirect:/userOrders/" + user.getId();
    }

    // Order Form
    @GetMapping("/orderForm")
    public String showOrderForm(@RequestParam(required = true) String userId, Model model) {
        try {
            if (userId == null || userId.isEmpty()) {
                model.addAttribute("errorMessage", "Authentication failed. Please log in.");
                return "login";
            }

            User user = userService.findById(userId).orElse(null);
            if (user == null) {
                model.addAttribute("errorMessage", "User not found. Please log in.");
                return "login";
            }

            model.addAttribute("user", user);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error loading order form: " + e.getMessage());
            return "login";
        }

        return "orderForm";
    }

    @PostMapping("/placeOrder")
    public String placeOrder(Order order, RedirectAttributes redirectAttributes) {
        try {
            System.out.println("Placing order for user: " + order.getUserId());

            Order newOrder = orderService.placeOrder(order);

            // Attempt to create a transaction
            restTemplate.postForObject("http://localhost:8082/transaction/createTransaction", newOrder, Boolean.class);

            // Attempt to create market order
            restTemplate.postForObject("http://localhost:8083/market/createMarketOrder", newOrder, Boolean.class);

            // Mark order as completed if successful
            newOrder.setStatus("completed");
            orderService.placeOrder(newOrder);

        } catch (Exception e) {
            System.out.println("Error during order process: " + e.getMessage());
            orderService.deleteOrder(order); // Delete failed order
            redirectAttributes.addFlashAttribute("errorMessage", "Order creation failed: " + e.getMessage());
            return "redirect:/userOrders/" + order.getUserId();
        }

        return "redirect:/userOrders/" + order.getUserId();
    }

    @GetMapping("/userOrders/{userId}")
    public String getUserOrders(@PathVariable String userId, Model model) {
        try {
            User user = userService.findById(userId).orElse(null);
            List<Order> orders = orderService.getUserOrders(userId);

            model.addAttribute("user", user);
            model.addAttribute("orders", orders);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error fetching orders: " + e.getMessage());
            return "userOrders";
        }

        return "userOrders";
    }

    @GetMapping("/sellOrder")
    public String sellStock(@RequestParam(required = true) String orderId, RedirectAttributes redirectAttributes) {
    	 Order order = orderService.getOrderById(orderId);

        try {           
            // Call to create a transaction
            restTemplate.postForObject("http://localhost:8082/transaction/sellTransaction", order, Boolean.class);

            // Call to sell stock in market
            restTemplate.postForObject("http://localhost:8083/market/sellMarketOrder", order, Boolean.class);

            orderService.deleteOrder(order); // Delete order after success

        } catch (Exception e) {
            System.out.println("Error during sell order process: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "An unexpected error occurred during the sell process.");
            return "redirect:/userOrders/" + order.getUserId();
        }

        return "redirect:/userOrders/" + order.getUserId();
    }

}
