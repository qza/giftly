package org.koko.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.jsonwebtoken.Claims;

@JsonInclude(content = JsonInclude.Include.NON_EMPTY)
public class AuthTokenResponse {

    private String token;
    private Claims claims;
    private String error;
    private String message;

    @JsonCreator
    public AuthTokenResponse(@JsonProperty("token") String token, @JsonProperty("message") String message) {
        this.token = token;
        this.message = message;
    }

    @JsonCreator
    public AuthTokenResponse(@JsonProperty("claims") Claims claims, @JsonProperty("message") String message) {
        this.claims = claims;
        this.message = message;
    }

    @JsonCreator
    public AuthTokenResponse(@JsonProperty("error") String error) {
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
