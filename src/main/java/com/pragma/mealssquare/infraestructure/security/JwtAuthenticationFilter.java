package com.pragma.mealssquare.infraestructure.security;

import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final IJwtTokenProvider iJwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();

        if ("/auth/login".equals(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<String> tokenOptional = Optional.ofNullable(request.getHeader(ConstantsErrorMessage.CONSTANT_HEADER_AUTHENTICATION))
                .filter(header -> header.startsWith(ConstantsErrorMessage.BEARER_PREFIX))
                .map(header -> header.substring(ConstantsErrorMessage.BEARER_SUBSTRING));

        if (tokenOptional.isPresent()) {
            String token = tokenOptional.get();

            if (iJwtTokenProvider.isTokenValid(token)) {
                Claims claims = iJwtTokenProvider.getClaims(token);
                String email = claims.getSubject();
                String rol = claims.get("rol",String.class);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(email,
                                null,
                                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + rol))
                        );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
