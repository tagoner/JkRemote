package com.adaptor;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.adaptor.inheritance.UserCheckAdaptee;
import com.model.UserCheckRepository;
import com.var.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(value = "userCheckAdaptor")
public class UserCheckAdaptor implements UserCheckAdaptee {
    
    @Resource(name = "userCheckRepository")
    UserCheckRepository userCheckRepository;
    
    
    @Override
    public User loadUserByUsername(String id) {
        /*
        if(findByDb(arg) == null)
            return null;
        if(findByLdap(arg) == null)
            return null;
        */
        
        return findByDb(id);
    }
    
    /*
     * DB 유저
     */
    private User findByDb(String id) {
        User user = userCheckRepository.findById(id);
        
        return user;
    }
    
    /*
     * LDAP 유저
     */
    private User findByLdap(User arg) {
        return arg;
    }
}