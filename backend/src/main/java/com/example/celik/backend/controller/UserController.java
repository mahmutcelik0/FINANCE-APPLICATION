package com.example.celik.backend.controller;

import com.example.celik.backend.exception.EmailNotFoundException;
import com.example.celik.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    private ResponseEntity getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            return new ResponseEntity(userService.getUserDetails(authentication.getName()).toString(), HttpStatus.OK);
        }catch (EmailNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}