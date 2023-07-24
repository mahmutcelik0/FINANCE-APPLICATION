package com.example.celik.backend.security;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthResponse {
    private String email;
    private String accessToken;

}
