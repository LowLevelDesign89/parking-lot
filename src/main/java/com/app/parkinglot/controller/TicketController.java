package com.app.parkinglot.controller;

import com.app.parkinglot.models.dto.request.ParkingSpotDeallocationRequestDTO;
import com.app.parkinglot.models.dto.request.VehicleDTO;
import com.app.parkinglot.models.entity.ParkingSpot;
import com.app.parkinglot.models.entity.Ticket;
import com.app.parkinglot.service.ParkingSpotReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parkingspots")
@AllArgsConstructor
public class TicketController {
    private ParkingSpotReservationService parkingSpotReservationService;

    @PutMapping("/allocate")
    public ResponseEntity<ParkingSpot> allocateSpotToVehicle(@RequestBody VehicleDTO vehicleDTO) {
        ParkingSpot allocatedParkingSpot = parkingSpotReservationService.allocateSpotToVehicle(vehicleDTO);
        return new ResponseEntity<>(allocatedParkingSpot, HttpStatus.OK);
    }

    @PutMapping("/deallocate")
    public ResponseEntity<Ticket> deallocateSpotToVehicle(@RequestBody ParkingSpotDeallocationRequestDTO request) {
        Ticket ticket = parkingSpotReservationService.deallocateParkingSpot(request);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }
}
