package com.example.demo.controller;

import com.example.demo.exception.ShopNoSuchElementException;
import com.example.demo.exception.SuchUserAlreadyExistException;
import java.util.logging.Level;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log
public class ExceptionControllerAdvice {

    @ExceptionHandler(
            {SuchUserAlreadyExistException.class, ShopNoSuchElementException.class, UsernameNotFoundException.class})
    private ResponseEntity<ErrorMessage> handleBadRequest(final Exception e) {
        log.log(Level.SEVERE, e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Data
    public static class ErrorMessage {

        private final String errorMessage;
    }
}
