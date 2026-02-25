package com.flight.flight_service.entiry;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "routes", uniqueConstraints = @UniqueConstraint(
    columnNames = {"source_airport_id", "destination_airport_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "source_airport_id")
    private Airport sourceAirport;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destination_airport_id")
    private Airport destinationAirport;
}
