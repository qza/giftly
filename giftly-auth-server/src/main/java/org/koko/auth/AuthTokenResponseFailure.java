package org.koko.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(content = JsonInclude.Include.NON_EMPTY)
public class AuthTokenResponseFailure {

    private String error;

    @JsonCreator
    public AuthTokenResponseFailure(@JsonProperty("error") String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
