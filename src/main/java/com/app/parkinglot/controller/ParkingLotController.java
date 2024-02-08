package com.app.parkinglot.controller;

import com.app.parkinglot.models.dto.request.ParkingLotDTO;
import com.app.parkinglot.models.dto.response.ParkingLotResponseDTO;
import com.app.parkinglot.models.entity.ParkingLot;
import com.app.parkinglot.service.ParkingLotService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/parkingLots")
public class ParkingLotController {
    private ParkingLotService parkingLotService;

    @PostMapping
    public ResponseEntity<ParkingLotResponseDTO> createParkingLot(@RequestBody ParkingLotDTO parkingLotDTO) {
        ParkingLotResponseDTO parkingLotResponseDTO = parkingLotService.createParkingLot(parkingLotDTO);
        return new ResponseEntity<>(parkingLotResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingLot> getParkingLotDetails(@PathVariable Long id) {
        ParkingLot parkingLot = parkingLotService.getParkingLotById(id);
        return new ResponseEntity<>(parkingLot, HttpStatus.OK);
    }

}
