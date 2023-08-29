package com.VirtualCard.maplerads.Service;

import com.VirtualCard.maplerads.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class CustomUserDetails implements UserDetailsService {

    @Autowired(required = false)
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }


}
