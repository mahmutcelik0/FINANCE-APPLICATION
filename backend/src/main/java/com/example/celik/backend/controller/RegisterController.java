package com.example.celik.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @GetMapping
    private String getRegisterDetails(){
        return "REGISTER DETAILS";
    }



}
