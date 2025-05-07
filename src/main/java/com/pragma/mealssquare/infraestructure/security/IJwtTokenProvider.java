package com.pragma.mealssquare.infraestructure.security;


import com.pragma.mealssquare.domain.model.User;
import io.jsonwebtoken.Claims;

public interface IJwtTokenProvider {
    String generateToken(User user);
    boolean isTokenValid(String token);
    Claims getClaims(String token);
}
