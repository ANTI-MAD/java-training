package com.example.demo.service;

import com.example.demo.dto.CustomerSignUpRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public String signUp(final CustomerSignUpRequest request) {
        return "{\"id\":1}";
    }
}