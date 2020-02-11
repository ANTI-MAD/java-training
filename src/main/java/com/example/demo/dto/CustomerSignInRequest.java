package com.example.demo.dto;

import lombok.Data;

@Data
public class CustomerSignInRequest {
    private String email;
    private String password;
}
