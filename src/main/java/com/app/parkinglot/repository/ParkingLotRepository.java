package com.app.parkinglot.repository;

import com.app.parkinglot.models.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
}
