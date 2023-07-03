package com.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.var.Token;
import com.var.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtProvider {
	
    @Value("${api.key}")
    private String KEY;
    
    //30분
	private static final long ACCESS_VALID_TIME = 1000L * 60 * 30;
	//1일
    private static final long REFRESH_VALID_TIME = 1000L * 60 * 60 * 24 * 1;
	
	
	//JWT 토큰 생성
	public String createToken(User user, Token type) throws Exception {
		Date current = new Date();
		
		Map<String, String> payload = CommonUtil.toMap(user);
	    Claims claims = Jwts.claims();
		claims.putAll(payload);
		
		String token = null;
		if(Token.ACCESS.compareTo(type) == 0) {
		    token = Jwts.builder()
		                //이름
		                .setSubject(user.getUsername())
	                    //Payload
	                    .setClaims(claims)
	                    //만기시간
	                    .setExpiration(new Date(current.getTime() + ACCESS_VALID_TIME))
	                    .compact();
		}
		else if(Token.REFRESH.compareTo(type) == 0) {
		    token = Jwts.builder()
    		            //Payload
                        .setClaims(claims)
                        //발행시간
                        .setIssuedAt(current)
                        //만기시간
                        .setExpiration(new Date(current.getTime() + REFRESH_VALID_TIME))
                        .compact();
		}
		
		return token;
	}
	
	//JWT 토큰 검증
	public Token validateToken(String token) {
	    Token flag = Token.SUCCESS;
	    
        try {
            Jwts.parserBuilder().setSigningKey(getEncriptKey(KEY)).build().parseClaimsJws(token);
        }
        catch(NullPointerException e) {
            log.error("JWT가 Null입니다");
            flag = Token.NULL;
        }
        catch(SecurityException | MalformedJwtException e) {
            log.error("잘못된 JWT 서명입니다");
            flag = Token.EXCEPTION;
        }
        catch(ExpiredJwtException e) {
            log.error("만료된 JWT입니다");
            flag = Token.EXPIRED;
        }
        catch(UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT입니다");
            flag = Token.EXCEPTION;
        }
        catch(IllegalArgumentException e) {
            log.error("JWT가 잘못되었습니다");
            flag = Token.EXCEPTION;
        }
        
        return flag;
    }
	
	//JWT 토큰 조회
	public Claims getClaims(String token) {
		return Jwts.parserBuilder()
		           .setSigningKey(getEncriptKey(KEY))
		           .build()
		           .parseClaimsJws(token)
		           .getBody();
	}
	
	//Authentication 생성
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        
        log.info("{}", claims);
        
        return null;
    }
	
	//HMAC_SHA 인코딩
    private Key getEncriptKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}