package com.flight.auth_service.dto;

import io.micrometer.common.lang.NonNull;
import lombok.Data;

@Data
public class RoleRequest {

    @NonNull
    private String roleName;
}
