package com.milaev.medicine.config;

import com.milaev.medicine.bean.SessionAuthentication;
import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dto.validators.AccountValidator;
import com.milaev.medicine.dto.validators.DoctorValidator;
import com.milaev.medicine.dto.validators.EventValidator;
import com.milaev.medicine.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

//@Configuration
//@ComponentScan("com.milaev.medicine")
public class TestConfig {

//    @Bean
//    public AccountValidator accountValidator() {
//        return new AccountValidator();
//    }
//
//    @Bean
//    public DoctorValidator doctorValidator() {
//        return new DoctorValidator();
//    }

    @Bean
    public EventValidator eventValidator() {
        return new EventValidator();
    }
//
//    @Bean
//    public SessionAuthenticationInterface sessionAuthentication() {
//        return new SessionAuthentication();
//    }

    @Bean
    public AccountService accountService() {
        return mock(AccountService.class);
    }
}
