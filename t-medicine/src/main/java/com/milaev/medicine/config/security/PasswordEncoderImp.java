package com.milaev.medicine.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//import org.apache.log4j.Logger;

@Component
public class PasswordEncoderImp implements PasswordEncoder {

    private static Logger log = LoggerFactory.getLogger(PasswordEncoderImp.class);

    // TODO is @Bean need if exist @Component?
    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("passwordEncoder()");
        return new PasswordEncoderImp();
        // return new BCryptPasswordEncoder();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        String rez = String.format("PW_%s", rawPassword.toString());
        log.info("passwordEncoder: encode " + rez);
        return rez;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        boolean rez = encodedPassword.equals(encode(rawPassword.toString()));
        log.info("passwordEncoder: matches " + rez);
        return rez;
    }
}