package com.pragma.mealssquare.infraestructure.security;


import com.pragma.mealssquare.domain.model.User;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;


@Component
@Slf4j
public class JwtTokenProvider implements IJwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long validateTime;

    private byte[] secretKey;

    @PostConstruct
    public void init(){
        this.secretKey = Base64.getDecoder().decode(jwtSecret);
    }

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("rol",user.getRol().getNameRol())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validateTime))
                .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            Claims claims = getClaims(token);
            boolean isValid = claims.getExpiration().after(new Date());
            if (!isValid) {
                log.warn("{}: {}", ConstantsErrorMessage.EXPIRED_TOKEN, claims.getSubject());
            }
            return isValid;
        } catch (JwtException | IllegalArgumentException e){
            log.error("{}: {}",ConstantsErrorMessage.ERROR_TO_VALIDATE_TOKEN, e.getMessage());
            throw new CustomException(ConstantsErrorMessage.ERROR_TO_VALIDATE_TOKEN);
        }
    }

    @Override
    public Claims getClaims(String token) {
        return (Claims) Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
