package com.flight.flight_service.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class FlightSearchRequest {
    private String source;
    private String destination;
    private LocalDate travelDate;
}
