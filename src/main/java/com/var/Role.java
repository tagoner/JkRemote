package com.var;

import lombok.Getter;

@Getter
public enum Role {
    
    USER("USER", "기본사용자"),
    ADMIN("ADMIN", "시스템관리자");
    
    
    private final String code;
    private final String name;
    
    Role(String code, String name) {
        this.code = code;
        this.name = name;
    }
}