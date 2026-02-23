package com.flight.auth_service.exception;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(Long id) {
        super("Role not found: "+ id);
    }

}
