package com.example.demo.config.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {

    private SecurityUtils() {
    }


    public static Optional<String> getUserId(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof User) {
                        User springSecurityUser = (User) authentication.getPrincipal();
                        return springSecurityUser.getUsername();
                    }
                    return null;
                });
    }



    public static AuthenticationToken handleAuthenticationToken(UserDetails userDetails) {
        AuthenticationToken authentication = new AuthenticationToken();
        // 保存用户信息
        authentication.setPrincipal(userDetails.getUsername());
        authentication.setDetails(userDetails);
        authentication.setAuthenticated(true);
        return authentication;
    }
}
