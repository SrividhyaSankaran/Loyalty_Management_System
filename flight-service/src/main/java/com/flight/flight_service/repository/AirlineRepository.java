package com.flight.flight_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.flight_service.entiry.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    
    Optional<Airline> findByCode(String code);

    boolean existsByCode(String code);
    
}
