package com.flight.flight_service.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.flight.flight_service.Flight;
import com.flight.flight_service.dto.FlightResponse;
import com.flight.flight_service.dto.FlightSearchRequest;
import com.flight.flight_service.repository.FlightRepository;

import jakarta.transaction.Transactional;
import lombok.*;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightService {

    private final FlightRepository flightRepository;

    @Cacheable(
        value = "flight-search",
        key = "#request.source + '-' + #request.destination + '-' + #request.travelDate"
    )
    public List<FlightResponse> searchFlights(FlightSearchRequest request) {

        List<Flight> flights = flightRepository.searchFlights(
                request.getSource(),
                request.getDestination(),
                request.getTravelDate()
        );

        return flights.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private FlightResponse mapToResponse(Flight flight) {

        return FlightResponse.builder()
                .id(flight.getId())
                .airline(flight.getAirline().getName())
                .flightNumber(flight.getFlightNumber())
                .source(flight.getRoute().getSourceAirport().getCode())
                .destination(flight.getRoute().getDestinationAirport().getCode())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .basePrice(flight.getBasePrice())
                .build();
    }
}