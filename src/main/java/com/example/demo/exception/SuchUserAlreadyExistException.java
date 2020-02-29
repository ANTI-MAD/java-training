package com.example.demo.exception;

/**
 * @author Wladimir Litvinov
 */
public class SuchUserAlreadyExistException extends Exception {

    public SuchUserAlreadyExistException() {
        super();
    }

    public SuchUserAlreadyExistException(final String message) {
        super(message);
    }
}
