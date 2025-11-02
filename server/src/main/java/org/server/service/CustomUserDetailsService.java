package org.server.service;

import org.server.modal.UserPrincipals;
import org.server.modal.Users;
import org.server.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipals(user);
    }

    public Users addUser(Users user){
        return userRepo.save(user);
    }
}
