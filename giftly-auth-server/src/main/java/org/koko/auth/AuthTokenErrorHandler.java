package org.koko.auth;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthTokenErrorHandler {

    @ExceptionHandler(value = AuthorizationServiceException.class)
    public ResponseEntity<AuthTokenResponseFailure> handleAuthorizationException(AuthenticationException cause) {
        AuthTokenResponseFailure response = new AuthTokenResponseFailure("authentication failed");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(value = JwtException.class)
    public ResponseEntity<AuthTokenResponseFailure> handleJwtException(JwtException cause) {
        AuthTokenResponseFailure response = new AuthTokenResponseFailure("invalid token");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
}
