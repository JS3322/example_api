package com.cleancode.example_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;

@Configuration
public class SecurityWebFilterChain {

    @Bean
    public org.springframework.security.web.server.SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .pathMatchers("/generate-token").permitAll()  // Allow unauthenticated access to generate-token
                .pathMatchers("/authenticate").permitAll()    // Allow unauthenticated access to authenticate
                .anyExchange().authenticated()                 // All other endpoints require authentication
                .and()
                .httpBasic();  // Use HTTP Basic Authentication

        return http.build();
    }
}
