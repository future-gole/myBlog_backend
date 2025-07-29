package com.doublez.myblog_backend.util;

//import io.jsonwebtoken.security.Keys;
//import io.jsonwebtoken.Jwts;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import java.security.Key;
//import java.util.Date;
//@Component
//public class JwtUtil {
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private Long expiration;
//
//    private Key getKey() {
//        return Keys.hmacShaKeyFor(secret.getBytes());
//    }
//
//    public String generateToken(String username) {
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + expiration);
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(getKey())
//                .compact();
//    }
//
//    // 未来可以添加验证和解析 Token 的方法
//    // public String getUsernameFromToken(String token) { ... }
//    // public boolean validateToken(String token) { ... }
//}