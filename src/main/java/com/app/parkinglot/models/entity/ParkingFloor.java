package com.app.parkinglot.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ParkingFloor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String floorDescription;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ParkingSpot> parkingSpots;
}
