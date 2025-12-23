package com.example.balkan_cars.auth;

import com.example.balkan_cars.user.UserDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    
    private final String SECRET = Base64.getEncoder().encodeToString("NikichataGalataGosheto123".getBytes());
    private final long EXPIRATION = 1000 * 60 * 60 * 10; // 10 hours

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(UserDto user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.id()); // Adds the UUID to the payload

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.email())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
