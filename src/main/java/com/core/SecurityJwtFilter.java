package com.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.filter.OncePerRequestFilter;

import com.adaptor.inheritance.UserCheckAdaptee;
import com.util.CommonUtil;
import com.util.CookieUtil;
import com.util.JwtProvider;
import com.var.Token;
import com.var.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SecurityJwtFilter extends OncePerRequestFilter {
    
    private final UserCheckAdaptee userCheckAdaptor;
    
    private final JwtProvider jwtProvider;
    
    
    @Override
    protected void doFilterInternal(@RequestBody HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String requestUrl = request.getRequestURI();
        Authentication authenticate = null;
        
        //로그인
        if(requestUrl.contains("/login")) {
            String id = request.getParameter("id");
            String pw = request.getParameter("pw");
            log.info("ID : {}", id);
            log.info("PW : {}", pw);
            
            Enumeration<String> paramKeys = request.getParameterNames();
            while (paramKeys.hasMoreElements()) {
                 String key = paramKeys.nextElement();
                 logger.info(key+":"+request.getParameter(key));
            }
            
            authenticate = createAuthentication(response, id, pw);
        }
        //API
        else if(requestUrl.contains("/api")) {
            authenticate = getAuthentication(request);
        }
        
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        chain.doFilter(request, response);
    }
    
    /*
     * 인증 생성
     */
    private Authentication createAuthentication(HttpServletResponse response, String id, String pw) {
        //유저 색인
        User user = userCheckAdaptor.loadUserByUsername(id);
        log.info("User : {}", user);
        
        //비밀번호 검증
        if(!user.getPassword().equals(pw)) {
            log.error("USER PW 불일치");
            
            return null;
        }
        //비밀번호 보안
        else {
            user.setPassword(null);
        }
        
        //권한 셋팅
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        
        //토큰 셋팅
        try {
            Map<String, String> param = new LinkedHashMap<>();
            
            //생성
            String accessToken = jwtProvider.createToken(user, Token.ACCESS);
            String refreshToken = jwtProvider.createToken(user, Token.REFRESH);
            
            //엑세스 -> 로컬스토리지
            response.setHeader(Token.ACCESS.getName(), accessToken);
            log.info("ACCESS : {}", accessToken);
            
            //리프레시 -> 쿠키
            param.put(Token.REFRESH.getName(), refreshToken);
            CookieUtil.setCookie(response, param);
            log.info("REFRESH : {}", refreshToken);
        }
        catch (Exception e) {
            log.error("{}", e);
        }
        
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), authorities);
    }
    
    /*
     * 인증 검색
     */
    private Authentication getAuthentication(HttpServletRequest request) {
        String accessToken = request.getHeader(Token.ACCESS.getName());
        String refreshToken = CookieUtil.getCookie(request, Token.REFRESH.getName());
        
        log.info("{}", accessToken);
        log.info("{}", refreshToken);
        
        return null;
    }
}