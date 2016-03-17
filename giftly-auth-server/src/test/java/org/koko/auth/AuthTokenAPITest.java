package org.koko.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.koko.GiftlyAuthServerApplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GiftlyAuthServerApplication.class)
@WebIntegrationTest(randomPort = true)
public class AuthTokenAPITest {

    @Value("${local.server.port}")
    private int port;

    private URL base;
    private RestTemplate template;

    public static final String TEST = "test";
    public static User user = new User(TEST, TEST, Arrays.asList(new SimpleGrantedAuthority(TEST)));

    @Before
    public void setUp() throws Exception {
        base = new URL("http://localhost:" + port + "/");
        template = new TestRestTemplate();
    }

    @Test
    public void shouldReturnErrorResponseForUnknownUser() throws Exception {

        AuthTokenRequest authTokenRequest = new AuthTokenRequest();
        authTokenRequest.setUsername(TEST);
        authTokenRequest.setPassword(TEST);

        RequestEntity<AuthTokenRequest> requestEntity =
                RequestEntity.post(new URI(base.toString() + "/auth/new"))
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .body(authTokenRequest);

        String responseStr = template.postForObject(requestEntity.getUrl(), requestEntity, String.class);
        AuthTokenResponse response = new ObjectMapper().readValue(responseStr, AuthTokenResponse.class);

        assertThat(response.getError(), response.getError().matches("invalid credentials"));
    }
}
