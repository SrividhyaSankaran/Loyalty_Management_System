package com.flight.flight_service.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flight.flight_service.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("""
        SELECT f FROM Flight f
        JOIN FETCH f.airline
        JOIN FETCH f.route r
        JOIN FETCH r.sourceAirport
        JOIN FETCH r.destinationAirport
        WHERE r.sourceAirport.code = :source
        AND r.destinationAirport.code = :destination
        AND DATE(f.departureTime) = :travelDate
        AND f.available = true
    """)
    List<Flight> searchFlights(
            String source,
            String destination,
            LocalDate travelDate
    );
}
