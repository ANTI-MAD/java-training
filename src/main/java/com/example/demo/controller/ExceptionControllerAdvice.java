package com.example.demo.controller;

import java.util.logging.Level;

import com.example.demo.exception.SuchUserAlreadyExistException;
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
            {SuchUserAlreadyExistException.class, UsernameNotFoundException.class})
    private ResponseEntity<ErrorMessage> handleBadRequest(final Exception e) {
        log.log(Level.SEVERE, e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorMessage> handle(final Exception e) {
//        log.log(Level.SEVERE, e.getMessage(), e);
//        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @Data
    public static class ErrorMessage {

        private final String errorMessage;
    }
}
