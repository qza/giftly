package org.koko.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {

    private final String tokenSecret;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthTokenService(@Value("${auth.token.secret}") String tokenSecret, UserDetailsService userDetailsService) {
        this.tokenSecret = tokenSecret;
        this.userDetailsService = userDetailsService;
    }

    public String createToken(User user) {
        return Jwts.builder().setSubject(user.getUsername()).signWith(SignatureAlgorithm.HS512, tokenSecret).compact();
    }

    public UserDetails detectUser(String token) {
        String username = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody().getSubject();
        return userDetailsService.loadUserByUsername(username);
    }

}

