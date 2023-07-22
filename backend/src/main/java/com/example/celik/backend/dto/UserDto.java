package com.example.celik.backend.dto;

import com.example.celik.backend.model.User;
import lombok.ToString;

import java.time.LocalDate;

public class UserDto {
    private String name;
    private String email;
    private LocalDate birth;

    public UserDto(String name, String email, LocalDate birth) {
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    public static UserDto perform(User user) {
        return new UserDto(user.getName(),user.getEmail(),user.getBirth());
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                '}';
    }
}
