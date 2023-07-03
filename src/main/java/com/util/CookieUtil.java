package com.util;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    
    public static String getCookie(HttpServletRequest request, String key) {
        String value = null;
        
        for(Cookie cookie : request.getCookies())
            if(key.equals(cookie.getName()))
                value = cookie.getValue();
        
        return value;
    }
    
    public static void setCookie(HttpServletResponse response, Map<String, String> arg) {
        arg.forEach((key, value) -> {
            Cookie cookie = new Cookie(key, value);
            cookie.setHttpOnly(true);
            
            response.addCookie(cookie);
        });
    }
}