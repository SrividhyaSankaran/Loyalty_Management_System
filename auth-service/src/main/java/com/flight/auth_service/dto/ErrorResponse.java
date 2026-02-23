package com.flight.auth_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private String error;
    private String message;
    private int status;
    private String path;
    private LocalDateTime timestamp;
}
