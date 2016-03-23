package org.koko.auth;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;

public class AuthTokenServiceTest {

    final String TEST_USER = "test-user";
    final String TEST_PASSWORD = "test-password";
    final String TEST_ROLE = "test-role";

    @Mock
    UserDetailsService userDetailsService = mock(UserDetailsService.class);

    User user = new User(TEST_USER, TEST_PASSWORD, Arrays.asList(new SimpleGrantedAuthority(TEST_ROLE)));
    AuthTokenService authTokenService = new AuthTokenService("token-secret", userDetailsService);

    @Before
    public void setUp() {
        when(userDetailsService.loadUserByUsername(TEST_USER)).thenReturn(user);
    }

    @After
    public void tearDown() {
        reset(userDetailsService);
    }

    @Test
    public void shouldGenerateTokenForUser() {
        assertNotNull(authTokenService.createToken(user));
    }

    @Test
    public void shouldDetectUserFromToken() {
        String token = authTokenService.createToken(user);
        UserDetails userDetails = authTokenService.detectUser(token);
        assertEquals(user, userDetails);
    }

}
