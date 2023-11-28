package org.aims.kpcu.auth.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.security.auth.Subject;






public class ManagerAuthenticationToken extends AbstractAuthenticationToken {

    private String username;

    private String password;

    public ManagerAuthenticationToken(String username, String password) {
        super(null);
        this.username=username;
        this.password=password;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public boolean implies(Subject subject) {
        return super.implies(subject);
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }
}
