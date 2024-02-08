package com.app.parkinglot.repository;

import com.app.parkinglot.models.entity.ParkingSpot;
import com.app.parkinglot.models.enums.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    Optional<ParkingSpot> findByVehicleNumberAndParkingSpotSize(String vehicleNumber, Size spotSize);

    List<ParkingSpot> findByParkingSpotSizeAndVehicleNumberIsNull(Size spotSize);
}
