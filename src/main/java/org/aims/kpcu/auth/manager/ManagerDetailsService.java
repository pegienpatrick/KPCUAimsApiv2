package org.aims.kpcu.auth.manager;

import org.aims.kpcu.entities.FManager;
import org.aims.kpcu.repositories.FManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerDetailsService implements UserDetailsService {

    @Autowired
    private FManagerRepository fManagerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<FManager> optionalFManager=fManagerRepository.findManagerByUsername(username);
        if(optionalFManager.isEmpty())
            throw new UsernameNotFoundException("Manager "+username+" does not exist");

        FManager fManager=optionalFManager.get();

        return new ManagerUserDetails(username,fManager.getPassword());
    }
}
