package com.flight.auth_service.exception;

public class UserAlreadyExistsException extends RuntimeException{ 

    public UserAlreadyExistsException(String username) {
        super("User already exists: " + username);
    }

}