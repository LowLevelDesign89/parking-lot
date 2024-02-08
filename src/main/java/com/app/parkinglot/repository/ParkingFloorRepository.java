package com.app.parkinglot.repository;

import com.app.parkinglot.models.entity.ParkingFloor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingFloorRepository extends JpaRepository<ParkingFloor, Long> {
}
