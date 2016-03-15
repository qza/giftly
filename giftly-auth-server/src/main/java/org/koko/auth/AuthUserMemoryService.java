package org.koko.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthUserMemoryService implements UserDetailsService {

    private final Map<String, UserDetails> store = new HashMap<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return store.get(username);
    }

    public void add(UserDetails userDetails) {
        store.put(userDetails.getUsername(), userDetails);
    }
}
