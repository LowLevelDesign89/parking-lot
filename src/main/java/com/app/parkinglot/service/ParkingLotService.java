package com.app.parkinglot.service;

import com.app.parkinglot.models.dto.request.ParkingLotDTO;
import com.app.parkinglot.models.dto.response.ParkingLotResponseDTO;
import com.app.parkinglot.models.entity.ParkingLot;

public interface ParkingLotService {
    ParkingLotResponseDTO createParkingLot(ParkingLotDTO parkingLotDTO);

    ParkingLot getParkingLotById(Long id);
}
