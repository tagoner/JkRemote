package com.model;

import org.springframework.stereotype.Repository;

import com.var.Role;
import com.var.User;

@Repository("userCheckRepository")
public class UserCheckRepository {
    
    public User findById(String id) {
        User user = User.builder()
                        .id("testId")
                        .pw("sd")
                        .role(Role.USER.getCode())
                        .build();
        
        return user;
    }
}