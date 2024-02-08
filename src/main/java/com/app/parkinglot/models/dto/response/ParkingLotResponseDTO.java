package com.app.parkinglot.models.dto.response;

import com.app.parkinglot.models.entity.Address;
import lombok.Data;

@Data
public class ParkingLotResponseDTO {
    private Long id;
    private String description;
    private Address address;
    private Integer numberOfFloors;
    private Integer spotsPerFloor;
}
