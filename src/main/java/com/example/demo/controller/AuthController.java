package com.example.demo.controller;

import com.example.demo.dto.UserSignUpRequest;
import com.example.demo.dto.UserSignInRequest;
import com.example.demo.dto.UserSignInResponse;
import com.example.demo.exception.SuchUserAlreadyExistException;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import java.util.List;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Api("Authentication")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final AuthService authService;

    @PostMapping(value = "/beer-shop-app/user/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "SignUp", notes = "Use this method to signUp new User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created"),
            @ApiResponse(code = 400, message = "User already exist")
    })
    public UserSignInResponse singUp(@ApiParam(value = "User signUp data", required = true)
                                         @RequestBody final UserSignUpRequest request)
            throws SuchUserAlreadyExistException {
        authService.signUp(request);
        return singIn(new UserSignInRequest(request.getEmail(), request.getPassword()));
    }

    @PostMapping(value = "/beer-shop-app/user/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "SignIn", notes = "Use this method to signIn, if user doesn't exist")
    public UserSignInResponse singIn(@ApiParam(value = "User signIn data", required = true)
                                         @RequestBody final UserSignInRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        return new UserSignInResponse(
                jwtUtil.generateToken(
                        new User(request.getEmail(), request.getPassword(),
                                List.of(new SimpleGrantedAuthority("CUSTOMER")))));
    }
}
