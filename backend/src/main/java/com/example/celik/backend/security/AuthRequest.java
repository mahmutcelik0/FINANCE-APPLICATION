package com.example.celik.backend.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthRequest {
    @NotNull
    @Email
    @Length(min = 5, max = 50)
    private String email;

    @NotNull
    @Length(min = 1, max = 50)
    private String name;

    @NotNull
    @Length(min = 5, max = 10)
    private String password;
}
