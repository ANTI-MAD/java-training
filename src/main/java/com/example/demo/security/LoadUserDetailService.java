package com.example.demo.security;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Data
public class LoadUserDetailService implements UserDetailsService {

    private final Map<String, String> inMemoryUsers = new HashMap<>();

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final String password = inMemoryUsers.get(username);
        if (password == null) {
            return null;
        } else {
            return new User(username, password, Collections.emptyList());
        }
    }
}
