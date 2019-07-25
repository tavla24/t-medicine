package com.milaev.medicine.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import com.milaev.medicine.service.interfaces.TPersistentTokenServiceInterface;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("accountDetailsService")
    UserDetailsService accountDetailsService;

    @Autowired
    @Qualifier("persistentTokenService")
    TPersistentTokenServiceInterface persistentTokenService;

    @Override
    @Autowired
    // TODO = public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
    // throws Exception {...}? + how/why?
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().and().csrf();

        http.authorizeRequests().antMatchers("/", "/index")
                .access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')");

        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ADMIN')");

        http.authorizeRequests().antMatchers("/dba-*").access("hasRole('ADMIN') or hasRole('DBA')");

        http.authorizeRequests().and().formLogin().loginPage("/login").loginProcessingUrl("/login")
                .usernameParameter("login").passwordParameter("password_hash");

        http.authorizeRequests().and().rememberMe().rememberMeParameter("remember-me")
                .tokenRepository(persistentTokenService).tokenValiditySeconds(86400);

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/access-denied");
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(accountDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
        PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
                "remember-me", accountDetailsService, persistentTokenService);
        return tokenBasedservice;
    }

    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }
}
