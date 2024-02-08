package com.app.parkinglot.service;

import com.app.parkinglot.models.dto.request.ParkingSpotDeallocationRequestDTO;
import com.app.parkinglot.models.dto.request.VehicleDTO;
import com.app.parkinglot.models.entity.ParkingSpot;
import com.app.parkinglot.models.entity.Ticket;

public interface ParkingSpotReservationService {
    ParkingSpot allocateSpotToVehicle(VehicleDTO vehicleDTO);

    Ticket deallocateParkingSpot(ParkingSpotDeallocationRequestDTO requestDTO);
}
