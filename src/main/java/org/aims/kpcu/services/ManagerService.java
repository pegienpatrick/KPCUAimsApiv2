package org.aims.kpcu.services;

import org.aims.kpcu.auth.manager.ManagerAuthenticationToken;
import org.aims.kpcu.models.manager.ManagerLoginRequest;
import org.aims.kpcu.models.manager.ManagerLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<ManagerLoginResponse> login(ManagerLoginRequest managerLoginRequest) {
        authenticationManager.authenticate(
                new ManagerAuthenticationToken(managerLoginRequest.getUsername(),managerLoginRequest.getPassword())
        );
        return null;
    }
}
