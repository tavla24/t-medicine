package com.milaev.medicine.config;

import com.milaev.medicine.bean.SessionAuthentication;
import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.service.AccountService;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

//@Configuration
//@ComponentScan("com.milaev.medicine")
public class TestConfig {

    @Bean
    public SessionAuthenticationInterface sessionAuthentication() {
        return mock(SessionAuthentication.class);
    }

    @Bean
    public AccountService accountService() {
        return mock(AccountService.class);
    }
}
