package com.milaev.medicine.config;

import com.milaev.medicine.dto.validators.AccountValidator;
import com.milaev.medicine.service.interfaces.AccountServiceInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {

    @Bean
    public AccountValidator accountValidator() {
        return new AccountValidator();
    }

    @Bean
    public AccountServiceInterface accountService() {
        return mock(AccountServiceInterface.class);
    }
}
