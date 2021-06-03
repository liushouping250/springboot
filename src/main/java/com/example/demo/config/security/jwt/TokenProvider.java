package com.example.demo.config.security.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.lang.UnknownClassException;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private final Base64.Encoder encoder = Base64.getEncoder();

    private SecretKey secretKey;

    private long tokenValidityInMilliseconds;

    private long tokenValidityInMillisecondsForRememberMe;


    @PostConstruct
    public void init() {

        byte[] keyBytes = Decoders.BASE64.decode("N2ZlZTU1YTJhNDdmZTE5ODJhZGZjN2U4NTAzNGVhNTM5OGU1YmM0ZjkwZDI3MzNmN2YxMDI0ZTA1MzM5YzgzNDUyNzIwNjA2ZGY3OTJkMTg2Mjk1MmE1YTBiNTAxN2RhZWJhZDI0NmJlMDNhNDU4M2JjOTg4ZjkzNTczOWZlMDg=");

        this.secretKey = Keys.hmacShaKeyFor(keyBytes);

        this.tokenValidityInMilliseconds =
                1000 * 86400L;
        this.tokenValidityInMillisecondsForRememberMe =
                60 * 365 * 24 * 3600 * 1000L ;
    }




    public String createToken(String username, boolean rememberMe) {
        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }
        log.info("username==="+username);
        return Jwts.builder()
                .setSubject(username)
                .claim(AUTHORITIES_KEY, "ROLE_ADMIN")
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        log.info("secretKey========="+secretKey.toString());
        log.info("token========="+token);
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        log.info("AUTHORITIES_KEY========="+claims.toString());
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        log.info("claims========="+claims.getSubject());
        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        log.info("进入解析方法");
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        } catch (UnknownClassException e){
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        }
        return false;
    }
}
