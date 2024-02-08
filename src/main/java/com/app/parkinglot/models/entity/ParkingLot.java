package com.app.parkinglot.models.entity;

import com.app.parkinglot.models.dto.response.ParkingLotResponseDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String description;

    @OneToOne
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ParkingFloor> parkingFloors;

    public static ParkingLotResponseDTO to(ParkingLot parkingLot) {
        ParkingLotResponseDTO parkingLotResponseDTO = new ParkingLotResponseDTO();
        parkingLotResponseDTO.setId(parkingLot.getId());
        parkingLotResponseDTO.setDescription(parkingLot.getDescription());
        parkingLotResponseDTO.setAddress(parkingLot.getAddress());
        parkingLotResponseDTO.setNumberOfFloors(parkingLot.getParkingFloors().size());
        parkingLotResponseDTO.setSpotsPerFloor(parkingLot.getParkingFloors().get(0).getParkingSpots().size());
        return parkingLotResponseDTO;
    }
}
