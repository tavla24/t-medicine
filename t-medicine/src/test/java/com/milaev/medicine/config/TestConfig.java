package com.milaev.medicine.config;

import com.milaev.medicine.dto.validators.AccountValidator;
import com.milaev.medicine.dto.validators.DoctorValidator;
import com.milaev.medicine.dto.validators.EventValidator;
import com.milaev.medicine.service.AccountService;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

//@Configuration
public class TestConfig {

    @Bean
    public AccountValidator accountValidator() {
        return new AccountValidator();
    }

    @Bean
    public DoctorValidator doctorValidator() {
        return new DoctorValidator();
    }

    @Bean
    public EventValidator eventValidator() {
        return new EventValidator();
    }

    @Bean
    public AccountService accountService() {
        return mock(AccountService.class);
    }
}
