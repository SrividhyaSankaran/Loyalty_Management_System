package com.flight.flight_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flight.flight_service.entiry.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query("""
        SELECT r FROM Route r
        JOIN FETCH r.sourceAirport
        JOIN FETCH r.destinationAirport
        WHERE r.sourceAirport.code = :source
        AND r.destinationAirport.code = :destination
    """)
    Optional<Route> findBySourceAndDestination(
            String source,
            String destination
    );

    boolean existsBySourceAirport_IdAndDestinationAirport_Id(
            Long sourceId,
            Long destinationId
    );
}