package com.var;

import lombok.Getter;

@Getter
public enum Token {
    
    ACCESS("ACCESS", "access_token"),
    REFRESH("REFRESH", "refresh_token"),
    SUCCESS("SUCCESS", "success_token"),
    EXPIRED("EXPIRED", "expired_token"),
    NULL("NULL", "null_token"),
    EXCEPTION("EXCEPTION", "exception_token");
    
    
    private final String code;
    private final String name;
    
    Token(String code, String name) {
        this.code = code;
        this.name = name;
    }
}