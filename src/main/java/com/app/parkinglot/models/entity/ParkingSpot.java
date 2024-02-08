package com.app.parkinglot.models.entity;

import com.app.parkinglot.models.enums.Size;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String spotDescription;

    @Enumerated(EnumType.STRING)
    private Size parkingSpotSize;
    private String vehicleNumber;
}
