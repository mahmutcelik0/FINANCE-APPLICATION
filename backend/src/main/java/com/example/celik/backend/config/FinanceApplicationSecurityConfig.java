package com.example.celik.backend.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class FinanceApplicationSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
            .sessionManagement(e -> e.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // JWT TOKEN ICIN STATELESS OLMASINI SAGLAR

            .cors(cors -> {
                cors.configurationSource(new CorsConfigurationSource() {
                @Override
                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                    corsConfiguration.setAllowCredentials(true); //user credential alabilmek için true ya setlendi
                    corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                    corsConfiguration.setExposedHeaders(Arrays.asList("Authorization")); //JWT token ı backend ten UI a yollamamızı sağlayacak
                    corsConfiguration.setMaxAge(3600L);

                    return corsConfiguration;
                }
                });
            })

//            .csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact", "/register","/role","/wealth").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) // bu pattern ları eklemeyince post put delete vs izin vermiyor
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->request
                    .requestMatchers("/dashboard","/role","/user","/wealth").authenticated()
                    .requestMatchers("/register","/home","/auth/**").permitAll())


                .httpBasic(Customizer.withDefaults())

                .formLogin(Customizer.withDefaults());

            return http.build();
    }

    //Hashing algorithm -> Bcrpyt password encoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
