package com.example.demo.service;

import com.example.demo.dto.CustomerSignInRequest;
import com.example.demo.dto.CustomerSignUpRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public String signUp(final CustomerSignUpRequest request) {
        return "{\"id\":1}";
    }

    public String signIn(final CustomerSignInRequest request) {
        return "{\"id\":1}";
    }
}