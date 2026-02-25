package com.flight.flight_service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.flight.flight_service.entiry.Airline;
import com.flight.flight_service.entiry.Route;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "route_id")
    private Route route;

    @Column(nullable = false)
    private String flightNumber;

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @Column(nullable = false)
    private BigDecimal basePrice;

    private boolean available = true;
}
