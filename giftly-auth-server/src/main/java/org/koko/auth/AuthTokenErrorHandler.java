package org.koko.auth;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthTokenErrorHandler {

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<AuthTokenResponse> handleAuthorizationException(AuthenticationException cause) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new AuthTokenResponse("invalid credentials"));
    }

    @ExceptionHandler(value = JwtException.class)
    public ResponseEntity<AuthTokenResponse> handleJwtException(JwtException cause) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new AuthTokenResponse("invalid token"));
    }

}
