package com.example.celik.backend.service;

import com.example.celik.backend.dto.UserDto;
import com.example.celik.backend.exception.EmailNotFoundException;
import com.example.celik.backend.model.User;
import com.example.celik.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public User findUserByEmail(String email) throws EmailNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new EmailNotFoundException("EMAIL NOT FOUND - BAD CREDENTIALS");
        }

        return user.get();
    }

    public ResponseEntity addNewUser(User user) {
        if(isExist(user.getEmail())){
            return new ResponseEntity("Email account already used", HttpStatus.CONFLICT);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roleService.getRolesByName(user.getRoles()));
        userRepository.save(user);
        return new ResponseEntity("Account has been created. Welcome!",HttpStatus.CREATED);
    }

    public UserDto getUserDetails(String email) throws EmailNotFoundException {
        if(!isExist(email)){
            throw new EmailNotFoundException("Bad credentials");
        }
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("Username is not correct");
        }

        return UserDto.perform(user.get());
    }

    public boolean isExist(String email){
        return userRepository.existsByEmail(email);
    }
}
