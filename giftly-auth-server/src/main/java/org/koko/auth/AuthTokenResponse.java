package org.koko.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;

@JsonInclude(content = JsonInclude.Include.NON_EMPTY)
public class AuthTokenResponse {

    private String token;
    private String message;
    private String error;

    @JsonDeserialize(as = DefaultClaims.class)
    private Claims claims;

    public AuthTokenResponse() {
    }

    public AuthTokenResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public AuthTokenResponse(Claims claims, String message) {
        this.claims = claims;
        this.message = message;
    }

    public AuthTokenResponse(String error) {
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
