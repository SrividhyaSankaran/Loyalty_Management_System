package com.flight.flight_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.*;

@Data
@Builder
public class FlightResponse {

    private Long id;
    private String airline;
    private String flightNumber;
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private BigDecimal basePrice;
}
