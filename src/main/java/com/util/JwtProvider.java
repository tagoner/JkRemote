package com.util;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component(value="Jwt")
public class JwtProvider {
	
    @Value("${api.key}")
    private String KEY;
    
	//만기시간 1000초
	private static long vaildTime = 1000L * 100000;
	
	
	//JWT 토큰 생성
	public String createToken(Map<String, String> payload) {
		Claims claims = Jwts.claims();
		claims.putAll(payload);
		
		Date current = new Date();
		return Jwts.builder()
		           //Payload
		           .setClaims(claims)
		           //발행시간
		           .setIssuedAt(current)
		           //만기시간
		           .setExpiration(new Date(current.getTime() + vaildTime))
		           .signWith(getEncriptKey(KEY), SignatureAlgorithm.HS256)
		           .compact();
	}
	
	//Key HMAC_SHA 인코딩
	private Key getEncriptKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
	
	//JWT 토큰 조회
	public Claims getClaims(String token) {
		return Jwts.parserBuilder()
		           .setSigningKey(getEncriptKey(KEY))
		           .build()
		           .parseClaimsJws(token)
		           .getBody();
	}
}