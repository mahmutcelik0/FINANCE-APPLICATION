package com.example.celik.backend.controller;

import com.example.celik.backend.config.FinanceApplicationUsernamePwdAuthenticationProvider;
import com.example.celik.backend.model.User;
import com.example.celik.backend.security.AuthRequest;
import com.example.celik.backend.security.AuthResponse;
import com.example.celik.backend.utils.JwtTokenUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class AuthApi {
    private final FinanceApplicationUsernamePwdAuthenticationProvider providerManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthApi(FinanceApplicationUsernamePwdAuthenticationProvider providerManager, JwtTokenUtil jwtTokenUtil) {
        this.providerManager = providerManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest authRequest){
        try {
            Authentication authentication = providerManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword())
            );

            User user = (User) authentication.getPrincipal();


            String accessToken = jwtTokenUtil.generateAccessToken(user);

            AuthResponse authResponse = new AuthResponse(user.getEmail(), accessToken);

            return ResponseEntity.ok().body(authResponse);
        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
