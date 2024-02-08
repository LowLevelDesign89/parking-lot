package com.app.parkinglot.models.dto.request;

import lombok.Data;

@Data
public class ParkingLotDTO {
    private Long parkingLotAddressId;
    private String description;
    private Integer numberOfFloors;
    //private Integer spotsPerFloor;
    private Integer largeSpotsPerFloor;
    private Integer mediumSpotsPerFloor;
    private Integer smallSpotsPerFloor;
}
