package com.app.parkinglot.models.entity;

import jakarta.persistence.*;
import lombok.Data;

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

    @OneToOne
    private Payment payment;
}
