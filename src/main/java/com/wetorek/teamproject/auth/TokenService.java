package com.wetorek.teamproject.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Service
class TokenService {

    private final SecretKey secretKey = Keys.secretKeyFor(HS512);

    @Value("${security.jwt.token.expire-length:3600000}")
    private final long validityInMilliseconds = 3600000; // 1h

    String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    String generateNewToken(UserDetails userDetails) {
        Map<String, Object> claims = Map.of(); // e.g. roles
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(key(), SignatureAlgorithm.HS512)
                .compact();
    }

    boolean isValidForUser(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return !isTokenExpired(token) && username.equals(userDetails.getUsername());
    }

    private Boolean isTokenExpired(String token) {
        return getAllClaimsFromToken(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(secretKey.getEncoded());
    }
}

