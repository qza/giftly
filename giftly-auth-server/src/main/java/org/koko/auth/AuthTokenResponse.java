package org.koko.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.jsonwebtoken.Claims;

@JsonInclude(content = JsonInclude.Include.NON_EMPTY)
public class AuthTokenResponse {

    private String token;

    private Claims claims;

    @JsonCreator
    public AuthTokenResponse(@JsonProperty("token") String token) {
        this.token = token;
    }

    @JsonCreator
    public AuthTokenResponse(@JsonProperty("claims") Claims claims) {
        this.claims = claims;
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
}
