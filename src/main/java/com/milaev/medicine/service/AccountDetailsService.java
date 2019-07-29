package com.milaev.medicine.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.milaev.medicine.model.Account;
import com.milaev.medicine.service.interfaces.TServiceInterface;

@Service
public class AccountDetailsService implements UserDetailsService {

	private static Logger LOGGER = LoggerFactory.getLogger(AccountDetailsService.class);

	@Autowired
	private TServiceInterface<Account> accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Account> users = accountService.getByLogin(username);
		Account user = users.get(0);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		LOGGER.info("loadUserByUsername(): " + user.getLogin());
		return new org.springframework.security.core.userdetails.User(user.getLogin(),
				Long.toString(user.getPassword_hash()), true, true, true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(Account user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getAccess_level().name()));
		LOGGER.info("getGrantedAuthorities(): " + user.getAccess_level().name());
		return authorities;
	}

}
