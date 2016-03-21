package org.koko.auth;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.koko.GiftlyAuthServerApplication;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GiftlyAuthServerApplication.class)
@WebIntegrationTest(randomPort = true)
public class AuthTokenAPITest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private AuthUserMemoryStore authUserMemoryStore;

    private URL base;
    private RestTemplate template;

    private User user = TestUserFactory.create();

    @Before
    public void setUp() throws Exception {
        base = new URL("http://localhost:" + port + "/");
        template = new TestRestTemplate();
    }

    @Test
    public void shouldReturnErrorResponseForUnknownUser() throws Exception {

        RequestEntity<AuthTokenRequest> requestEntity = new AuthTokenRequestFactory().create();
        String responseStr = template.postForObject(requestEntity.getUrl(), requestEntity, String.class);
        AuthTokenResponse response = new ObjectMapper().readValue(responseStr, AuthTokenResponse.class);

        assertThat(response.getError(), response.getError().matches("invalid credentials"));
    }

    @Test
    public void shouldReturnTokenForKnownUser() throws Exception {

        authUserMemoryStore.add(user);

        RequestEntity<AuthTokenRequest> requestEntity = new AuthTokenRequestFactory().create();
        String responseStr = template.postForObject(requestEntity.getUrl(), requestEntity, String.class);
        AuthTokenResponse response = new ObjectMapper().readValue(responseStr, AuthTokenResponse.class);

        assertThat("produced valid token", response.getToken() != null);
    }

    private static final String TEST = "test";

    private static class TestUserFactory {
        /**
         * @return test user
         */
        private static User create() {
            return new User(TEST, TEST, Arrays.asList(new SimpleGrantedAuthority(TEST)));
        }
    }

    private class AuthTokenRequestFactory {
        /**
         * @return spring http request entity
         * @throws URISyntaxException
         */
        private RequestEntity<AuthTokenRequest> create() throws URISyntaxException {
            AuthTokenRequest authTokenRequest = new AuthTokenRequest();
            authTokenRequest.setUsername(TEST);
            authTokenRequest.setPassword(TEST);
            return RequestEntity.post(new URI(base.toString() + "/auth/new"))
                    .accept(MediaType.APPLICATION_JSON_UTF8)
                    .body(authTokenRequest);
        }
    }
}
