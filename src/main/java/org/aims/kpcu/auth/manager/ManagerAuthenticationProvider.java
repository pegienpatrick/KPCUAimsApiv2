package org.aims.kpcu.auth.manager;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ManagerAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private ManagerDetailsService managerDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        ManagerAuthenticationToken managerAuthenticationToken= (ManagerAuthenticationToken) authentication;
        UserDetails managerUserDetails=managerDetailsService.loadUserByUsername(managerAuthenticationToken.getUsername());
        if(passwordEncoder.matches(managerAuthenticationToken.getPassword(),managerUserDetails.getPassword()))
            return managerAuthenticationToken;
        else{
            throw new AuthenticationException("Incorrect Password"){};
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ManagerAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
