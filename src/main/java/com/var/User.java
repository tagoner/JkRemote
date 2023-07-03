package com.var;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class User implements UserDetails {
    
    private String id;
    private String pw;
    private String role;
    
    
    @Override
    public String getUsername() {
        return id;
    }
    public void setUsername(String id) {
        this.id = id;
    }
    
    @Override
    public String getPassword() {
        return pw;
    }
    public void setPassword(String pw) {
        this.pw = pw;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        
        for(String role : role.split(","))
            authorities.add(new SimpleGrantedAuthority(role));
        
        return authorities;
    }
    public void setAuthorities(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
}