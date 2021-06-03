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
//        log.info(username+"@@@@@@@@@");
//        if (username!=null && !"".equals(username)) {
//            log.info("sdadadssa");
//            Optional<Store> oneByPhoneNumber = storeRepository.findByUsername(username);
//            log.info(oneByPhoneNumber.toString());
//            return oneByPhoneNumber.map(store -> createSpringSecurityUser(username, store))
//                    .orElseThrow(() -> new UsernameNotFoundException("User with phoneNumber " + username + " was not found in the database"));
//        }
//        log.info("进入此方法");
        throw new RuntimeException("User with userName " + username + " was not found in the database");
    }

//    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, Store store) {
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        return new org.springframework.security.core.userdetails.User(store.getUsername(),
//                store.getPassword(),
//                grantedAuthorities);
//    }
}
