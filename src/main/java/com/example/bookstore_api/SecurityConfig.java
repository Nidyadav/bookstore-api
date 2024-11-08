package com.example.bookstore_api;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
