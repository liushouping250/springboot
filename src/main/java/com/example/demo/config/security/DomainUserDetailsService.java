package com.example.demo.config.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
@Slf4j
public class DomainUserDetailsService implements UserDetailsService {


    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {

        throw new RuntimeException("User with userName " + username + " was not found in the database");
    }
}
