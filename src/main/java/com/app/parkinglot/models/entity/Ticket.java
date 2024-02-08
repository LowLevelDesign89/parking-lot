package com.app.parkinglot.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vehicleNumber;
    @ManyToOne
    private ParkingSpot parkingSpot;
    private Long entryTime;
    private Long exitTime;
    private BigDecimal price;
}
