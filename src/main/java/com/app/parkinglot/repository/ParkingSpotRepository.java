package com.app.parkinglot.repository;

import com.app.parkinglot.models.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
}
