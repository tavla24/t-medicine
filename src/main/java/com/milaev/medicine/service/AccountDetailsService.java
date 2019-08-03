package com.milaev.medicine.service;

import java.util.ArrayList;
import java.util.List;

import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.service.interfaces.AccountServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.milaev.medicine.model.Account;

@Service
public class AccountDetailsService implements UserDetailsService {

    private static Logger log = LoggerFactory.getLogger(AccountDetailsService.class);

    @Autowired
    private AccountServiceInterface accountService;

    @Autowired
    @Qualifier("passwordEncoder")
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDTO user = accountService.getByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        log.info("loadUserByUsername(): " + user.getLogin());

        UserBuilder builder = User.withUsername(username);
        // TODO passwordEncoder.encode(user.getPassword();
        builder.password(user.getPassword());
        builder.roles(user.getRole().getType());

        return builder.build();

//        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true, true,
//                true, true, getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Account user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getType()));
        log.info("getGrantedAuthorities(): " + user.getRole().getType());
        return authorities;
    }

}
