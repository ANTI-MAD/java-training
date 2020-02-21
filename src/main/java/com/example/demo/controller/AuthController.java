package com.example.demo.controller;

import com.example.demo.dto.UserSignInRequest;
import com.example.demo.dto.CustomerSignUpRequest;
import com.example.demo.dto.UserSignInResponse;
import com.example.demo.exception.SuchUserAlreadyExistException;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Data
@RestController
@RequestMapping("/beer-shop-app/user")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final AuthService authService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserSignInResponse signUp(@RequestBody final CustomerSignUpRequest request)
        throws SuchUserAlreadyExistException {
        authService.signUp(request);
        return singIn(new UserSignInRequest(request.getEmail(), request.getPassword()));
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserSignInResponse singIn(@RequestBody final UserSignInRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        return new UserSignInResponse(
                jwtUtil.generateToken(
                        new User(request.getEmail(), request.getPassword(), List.of(new SimpleGrantedAuthority("CUSTOMER")))));
    }

}
