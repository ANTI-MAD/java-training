package com.example.demo.controller;

import com.example.demo.dto.CustomerSignUpRequest;
import com.example.demo.service.CustomerService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Log
@Data
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String signUp(@RequestBody final CustomerSignUpRequest request) {
        return customerService.signUp(request);
    }

}
