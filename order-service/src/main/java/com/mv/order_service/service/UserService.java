package com.mv.order_service.service;

import com.mv.order_service.model.User;
import com.mv.order_service.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public Optional<User> findById(String Id){
    	return userRepository.findById(Id);
    }
    
    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
