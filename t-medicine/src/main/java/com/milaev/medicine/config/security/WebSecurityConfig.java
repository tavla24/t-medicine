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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("passwordEncoder")
    PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("accountDetailsService")
    UserDetailsService accountDetailsService;

    @Autowired
    @Qualifier("persistentTokenService")
    PersistentTokenRepository persistentTokenService;

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.userDetailsService(accountDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/test/**", "/").permitAll().anyRequest();

        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

        http.authorizeRequests().antMatchers("/doctor/**").hasRole("DOCTOR");

        http.authorizeRequests().antMatchers("/patient/**", "/recipe/**").hasAnyRole("ADMIN", "DOCTOR");

        http.authorizeRequests().antMatchers("/event/**").hasAnyRole("NURSE", "DOCTOR", "ADMIN");

        http.authorizeRequests().and().formLogin().loginPage("/login").loginProcessingUrl("/logincmd")
                .defaultSuccessUrl("/access_granted").usernameParameter("login").passwordParameter("password");

        http.authorizeRequests().and().logout().logoutUrl("/logoutcmd").logoutSuccessUrl("/");

        http.authorizeRequests().and().rememberMe().rememberMeParameter("remember-me")
                .tokenRepository(persistentTokenService).tokenValiditySeconds(86400);

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/access_denied");
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        //log.info("passwordEncoder()");
        return new BCryptPasswordEncoder();
    }
}
