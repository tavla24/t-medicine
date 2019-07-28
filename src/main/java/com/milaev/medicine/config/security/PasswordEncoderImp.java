package com.milaev.medicine.config.security;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderImp implements PasswordEncoder{
    
	private static Logger LOGGER = Logger.getLogger(PasswordEncoderImp.class);
	
    // TODO is @Bean need if exist @Component?
    @Bean
    public PasswordEncoder passwordEncoder() {
    	LOGGER.info("passwordEncoder()");
        return new PasswordEncoderImp();
    	//return new BCryptPasswordEncoder();
    }

    @Override
    public String encode(CharSequence rawPassword) {
    	String rez = rawPassword.toString();
    	LOGGER.info("passwordEncoder: encode " + rez);
        return rez;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
    	boolean rez = encodedPassword.equals(rawPassword.toString());
    	LOGGER.info("passwordEncoder: matches " + rez);
        return rez;
    }
}
