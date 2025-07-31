package com.example.fresher_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Tắt CSRF
            .authorizeHttpRequests()
                .anyRequest().permitAll(); // Cho phép tất cả các endpoint không cần login
        return http.build();
    }
}
