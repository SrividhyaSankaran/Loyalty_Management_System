package com.flight.auth_service.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserRequest {
    
    @NonNull
    private String username;

    @NonNull
    @Email
    private String email;

    @NonNull
    private String password;

    @NonNull
    private List<Long> roleIds;

    @NonNull
    private String status;

}
