package org.koko.auth;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class AuthTokenAPI {

    private final AuthTokenService authTokenService;

    @Autowired
    public AuthTokenAPI(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    @RequestMapping(value = "/auth/new", method = POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AuthTokenResponse> create(@RequestBody AuthTokenRequest authTokenRequest) {
        String token = authTokenService.createToken(authTokenRequest.getUsername(), authTokenRequest.getPassword());
        AuthTokenResponse response = new AuthTokenResponse(token, "1h");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RequestMapping(value = "/auth/claim", method = POST)
    public ResponseEntity<AuthTokenResponse> claim(@RequestHeader("X-AUTH-TOKEN") String authToken) {
        Claims claims = authTokenService.detectClaims(authToken);
        AuthTokenResponse response = new AuthTokenResponse(claims, "expires: " + claims.getExpiration());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}