package com.example.fresher_manager.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresher_manager.service.JWTService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestParam String username,
            @RequestParam String password) {
        
        // Xác thực credentials
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );

        // Load user details
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        
        // Generate token
        final String jwtToken = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(Map.of("token", jwtToken));
    }
}