package com.example.demo.service;

import com.example.demo.dto.UserSignInRequest;
import com.example.demo.dto.CustomerSignUpRequest;
import com.example.demo.exception.SuchUserAlreadyExistException;
import com.example.demo.security.LoadUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final LoadUserDetailService loadUserDetailService;

    public void signUp(final CustomerSignUpRequest request) throws SuchUserAlreadyExistException {
        if (loadUserDetailService.loadUserByUsername(request.getEmail()) != null) {
            throw new SuchUserAlreadyExistException();
        }
        loadUserDetailService.saveUser(request.getEmail(), request.getPassword());
    }

    public String signIn(final UserSignInRequest request) {
        return "{\"id\":1}";
    }
}