package com.adaptor.inheritance;

import com.var.User;

public interface UserCheckAdaptee {
    
    public User loadUserByUsername(String id);
}