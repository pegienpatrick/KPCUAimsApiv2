package org.aims.kpcu.services;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.aims.kpcu.auth.manager.ManagerAuthenticationToken;
import org.aims.kpcu.auth.manager.ManagerDetailsService;
import org.aims.kpcu.auth.manager.ManagerUserDetails;
import org.aims.kpcu.configurations.WebSecurityConfig;
import org.aims.kpcu.models.manager.ManagerLoginRequest;
import org.aims.kpcu.models.manager.ManagerLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ManagerService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ManagerDetailsService managerDetailsService;

    public ResponseEntity<ManagerLoginResponse> login(ManagerLoginRequest managerLoginRequest) {
        ManagerAuthenticationToken managerAuthenticationToken= (ManagerAuthenticationToken) authenticationManager.authenticate(
                new ManagerAuthenticationToken(managerLoginRequest.getUsername(),managerLoginRequest.getPassword())
        );

        ManagerUserDetails managerUserDetails=managerDetailsService.loadUserByUsername(managerAuthenticationToken.getUsername());


        Collection<? extends GrantedAuthority> authorities = managerUserDetails.getAuthorities();


        // Create claims as a Map<String, Object>
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_type","manager");
        claims.put("username",managerUserDetails.getUsername());
        claims.put("authorities", authorities);


        // Set expiration time (adjust as needed)
        long expirationMillis = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1);
        Date expirationDate = new Date(expirationMillis);

        // Build and sign the JWT
        String authorization = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, WebSecurityConfig.secretKey)
                .compact();
//        String authorization= String.valueOf(Jwts.builder().setClaims( managerUserDetails.getAuthorities()))

        ManagerLoginResponse managerLoginResponse=ManagerLoginResponse.builder()
                .message("Login Sucessful")
                .authorization("Bearer "+authorization)
                .build();

        return ResponseEntity.ok(managerLoginResponse);
    }
}
