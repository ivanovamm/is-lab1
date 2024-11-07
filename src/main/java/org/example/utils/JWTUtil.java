package org.example.utils;


import jakarta.inject.Inject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.models.User;

import javax.crypto.SecretKey;
import java.util.Date;

public class JWTUtil {
    private static final String SECRET = "d4fG6pJ2sL8nR9zB7xK1qP3mY9tV6eW4sF2xM9jQ2uA7rL5nQ3yK8bN7tZ4oV1yM";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    private final long EXPIRATION_TIME = 86400000;

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
