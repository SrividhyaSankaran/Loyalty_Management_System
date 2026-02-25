package com.flight.flight_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.flight_service.entiry.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByCode(String code);

    boolean existsByCode(String code);
}
