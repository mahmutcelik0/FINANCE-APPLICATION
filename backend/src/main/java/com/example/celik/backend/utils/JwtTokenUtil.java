package com.example.celik.backend.utils;

import com.example.celik.backend.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24

    private static String SECRET_KEY = "abcdefghijklmnOPQRSTUVWXYZ";

    //2

    /**
     *
     * Subject is combination of the user’s ID and email, separated by a comma.
     * Issuer name is CodeJava
     * The token is issued at the current date and time
     * The token should expire after 24 hours
     * The token is signed using a secret key, which you can specify in the application.properties file or from system environment variable. And the signature algorithm is HMAC using SHA-512.
     * Generates JWT
     * */
    public String generateAccessToken(User user){
        return Jwts.builder()
                .setSubject(String.format("%s,%s",user.getName(),user.getEmail()))
                .setIssuer("FinanceApplication")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
    }

}
