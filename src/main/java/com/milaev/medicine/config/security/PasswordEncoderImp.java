package com.milaev.medicine.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderImp implements PasswordEncoder{
    
    // TODO is @Bean need if exist @Component?
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoderImp();
        //new BCryptPasswordEncoder();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(rawPassword.toString());
    }
}
