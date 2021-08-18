package com.atm_machine_web.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String s) {
        super(s);
    }
}
