package com.shiproutes.shared.infrastructure.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtils {

    private final SecretKey secretKey;
    private final Long expirationTime;

    public JwtUtils(String signingKey, Integer expirationTimeInMinutes) {
        this.secretKey = Keys.hmacShaKeyFor(signingKey.getBytes());
        this.expirationTime = expirationTimeInMinutes * 60000L;
    }

    public String generateToken(JwtInfo info) {
        return Jwts.builder()
            .claim("id", info.id())
            .claim("username", info.username())
            .claim("email", info.email())
            .claim("role", info.role())
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
            .signWith(secretKey)
            .compact();
    }

    public JwtInfo parseToken(String token) {
        var claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody();

        return new JwtInfo(
            claims.get("id", String.class),
            claims.get("username", String.class),
            claims.get("email", String.class),
            claims.get("role", String.class)
        );
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
