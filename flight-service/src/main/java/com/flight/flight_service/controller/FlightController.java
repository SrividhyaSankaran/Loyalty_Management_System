package com.flight.flight_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.flight.flight_service.dto.FlightResponse;
import com.flight.flight_service.dto.FlightSearchRequest;
import com.flight.flight_service.service.FlightService;

import lombok.*;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PreAuthorize("hasAuthority('BOOK_FLIGHT')")
    @PostMapping("/search")
    public ResponseEntity<List<FlightResponse>> searchFlights(
            @RequestBody FlightSearchRequest request) {

        return ResponseEntity.ok(
                flightService.searchFlights(request)
        );
    }
}
