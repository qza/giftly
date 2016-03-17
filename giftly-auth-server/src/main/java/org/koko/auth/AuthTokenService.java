package org.koko.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
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

    public String createToken(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new AuthenticationCredentialsNotFoundException(username);
        }
        if(!userDetails.getPassword().equals(password)) {
            throw new BadCredentialsException("invalid credentials");
        }
        return createToken(userDetails);
    }

    public String createToken(UserDetails user) {
        return Jwts.builder().setSubject(user.getUsername()).signWith(SignatureAlgorithm.HS512, tokenSecret).compact();
    }

    public Claims detectClaims(String token) {
        return Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
    }

    public UserDetails detectUser(String token) {
        return userDetailsService.loadUserByUsername(detectClaims(token).getSubject());
    }

}

