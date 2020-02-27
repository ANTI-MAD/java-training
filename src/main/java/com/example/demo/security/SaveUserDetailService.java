package com.example.demo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SaveUserDetailService {
    private final PasswordEncoder passwordEncoder;
    private final LoadUserDetailService loadUserDetailService;

    public void saveUser(final String username, final String password) {
        loadUserDetailService.getInMemoryUsers().put(username, passwordEncoder.encode(password));
    }
}
