package com.pragma.mealssquare.infraestructure.security;

import com.pragma.mealssquare.domain.model.TypeRolEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers("/meals-square/list-restaurants").permitAll()
                        .requestMatchers("/dish/list-dishes").permitAll()
                        .requestMatchers("/order/create-order").permitAll()
                        .requestMatchers("/meals-square/create-restaurant").hasRole(TypeRolEnum.ADMIN.name())
                        .requestMatchers("/dish/create-dish").hasRole(TypeRolEnum.OWNER.name())
                        .requestMatchers("/dish/update-dish").hasRole(TypeRolEnum.OWNER.name())
                        .requestMatchers("/dish/status-dish").hasRole(TypeRolEnum.OWNER.name())
                        .requestMatchers("/employee/create-employee").hasRole(TypeRolEnum.OWNER.name())
                        .requestMatchers("/order/list-order").hasRole(TypeRolEnum.EMPLOYEE.name())
                        .requestMatchers("/order/assign-order").permitAll()
                        .requestMatchers("/order/update-status-order").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
