package com.example.demo.controller;

import com.example.demo.dto.CustomerSignInRequest;
import com.example.demo.dto.CustomerSignUpRequest;
import com.example.demo.service.CustomerService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/beerShop-app/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String signUp(@RequestBody final CustomerSignUpRequest request) {
        return customerService.signUp(request);
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String singIn(@RequestBody final CustomerSignInRequest request) {
        return customerService.signIn(request);
    }

}
