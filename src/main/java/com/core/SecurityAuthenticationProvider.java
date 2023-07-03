package com.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.adaptor.inheritance.UserCheckAdaptee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.CookieUtil;
import com.util.JwtProvider;
import com.var.Token;
import com.var.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityAuthenticationProvider {//implements AuthenticationProvider, AuthenticationSuccessHandler {
    
    private final UserCheckAdaptee userCheckAdaptor;
    
    private final JwtProvider jwtProvider;
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    /*
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) {
        Authentication token =  null;
        String id = null;
        String pw = null;
        
        try {
            id = authentication.getPrincipal().toString();
            pw = authentication.getCredentials().toString();
        }
        catch(Exception e) {
            log.error("{}", e);
            
            throw e;
        }
        
        User user = userCheckAdaptor.userCheck(id);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        
        if(user != null) {
            token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), authorities);
            ((UsernamePasswordAuthenticationToken)token).setDetails(authentication.getDetails());
        }
        
        log.info("AuthenticationDetails : {}", authentication.getDetails());
        return token;
    }
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String, String> payload = new LinkedHashMap<String, String>();
        payload.put("id", authentication.getPrincipal().toString());
        payload.put("pw", authentication.getCredentials().toString());
        
        payload.put("role", authentication.getAuthorities().toString());
        
        Map<String, String> refreshToken = new LinkedHashMap<String, String>();
        refreshToken.put(Token.REFRESH.getName(), jwtProvider.createToken(payload, Token.REFRESH));
        CookieUtil.setCookie(response, refreshToken);
        
        Map<String, String> accessToken = new LinkedHashMap<String, String>();
        accessToken.put(Token.ACCESS.getName(), jwtProvider.createToken(payload, Token.ACCESS))
    }
    */
}