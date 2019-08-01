package com.milaev.medicine.bean;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;

@Component
public class SessionAuthentication implements SessionAuthenticationInterface {

    private static Logger log = LoggerFactory.getLogger(SessionAuthentication.class);

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    // TODO another way? by global context, ...
    @Override
    public String getUserName() {
        return getPrincipal();
    }

    @Override
    public boolean isAnonimusSession() {
        return isCurrentAuthenticationAnonymous();
    }

    @SuppressWarnings("unchecked")
    private String getPrincipal() {
        log.info("getPrincipal()");
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            log.info("getPrincipal(): instanceof UserDetails");
            userName = ((UserDetails) principal).getUsername();
            log.info("getPrincipal() UserDetails userName: " + userName);

            Collection<GrantedAuthority> cga = ((Collection<GrantedAuthority>) ((UserDetails) principal)
                    .getAuthorities());
            Iterator<GrantedAuthority> icga = cga.iterator();
            while (icga.hasNext())
                log.info("getPrincipal() UserDetails Authority: " + icga.next().getAuthority());
        } else {
            userName = principal.toString();
            log.info("getPrincipal() principal.toString() userName: " + userName);
        }
        return userName;
    }

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

}
