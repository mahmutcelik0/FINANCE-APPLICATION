package com.example.celik.backend.config;

import com.example.celik.backend.exception.EmailNotFoundException;
import com.example.celik.backend.model.User;
import com.example.celik.backend.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component()
public class FinanceApplicationUsernamePwdAuthenticationProvider implements AuthenticationProvider {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public FinanceApplicationUsernamePwdAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        try {
            User user = userService.findUserByEmail(authentication.getName());

            boolean matches = passwordEncoder.matches(authentication.getCredentials().toString(),user.getPassword());

            if(!matches){
                throw new BadCredentialsException("BAD CREDENTIALS - Password is not correct");
            }
            List<GrantedAuthority> roles = new ArrayList<>();
            user.getRoles().forEach(e-> roles.add(new SimpleGrantedAuthority(e.getRoleName())));

            return new UsernamePasswordAuthenticationToken(user,authentication.getCredentials().toString(),roles);
        } catch (EmailNotFoundException e) {
            throw new AuthenticationException(e.getMessage()) {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication)); //AbstractUserDetailsAuthProvider dan copy
    }
}
