package com.example.celik.backend.service;

import com.example.celik.backend.exception.EmailNotFoundException;
import com.example.celik.backend.model.User;
import com.example.celik.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String email) throws EmailNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new EmailNotFoundException("EMAIL NOT FOUND - BAD CREDENTIALS");
        }

        return user;
    }
}
