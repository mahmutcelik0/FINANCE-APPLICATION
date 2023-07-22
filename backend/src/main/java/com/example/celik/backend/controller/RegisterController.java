package com.example.celik.backend.controller;

import com.example.celik.backend.model.User;
import com.example.celik.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity addNewUser(@RequestBody User user){
        return userService.addNewUser(user);
    }
}