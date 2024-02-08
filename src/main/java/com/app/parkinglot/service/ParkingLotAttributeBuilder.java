package com.app.parkinglot.service;

import com.app.parkinglot.models.dto.request.ParkingLotDTO;
import com.app.parkinglot.models.entity.ParkingFloor;
import com.app.parkinglot.models.entity.ParkingSpot;
import com.app.parkinglot.models.enums.Size;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParkingLotAttributeBuilder {
    private final static String FLOOR_PREFIX = "FL";

    public List<ParkingFloor> buildParkingFloors(ParkingLotDTO parkingLotDTO) {
        List<ParkingFloor> parkingFloors = new ArrayList<>();
        for(int i = 1; i <= parkingLotDTO.getNumberOfFloors(); i++) {
            parkingFloors.add(buildParkingFloor(parkingLotDTO, i));
        }
        return parkingFloors;
    }

    public ParkingFloor buildParkingFloor(ParkingLotDTO parkingLotDTO, int floorNumber) {
        ParkingFloor parkingFloor = new ParkingFloor();
        parkingFloor.setFloorDescription(FLOOR_PREFIX + floorNumber);

        List<ParkingSpot> parkingSpotList = new ArrayList<>();
        parkingSpotList.addAll(buildSpots(parkingLotDTO.getLargeSpotsPerFloor(), Size.LARGE, parkingFloor.getFloorDescription()));
        parkingSpotList.addAll(buildSpots(parkingLotDTO.getMediumSpotsPerFloor(), Size.MEDIUM, parkingFloor.getFloorDescription()));
        parkingSpotList.addAll(buildSpots(parkingLotDTO.getSmallSpotsPerFloor(), Size.SMALL, parkingFloor.getFloorDescription()));
        parkingFloor.setParkingSpots(parkingSpotList);
        return parkingFloor;
    }

    public List<ParkingSpot> buildSpots(int numSpots, Size spotSize, String floor) {
        List<ParkingSpot> parkingSpotList = new ArrayList<>();
        for(int currentSpot = 1; currentSpot <= numSpots; currentSpot++) {
            ParkingSpot parkingSpot = new ParkingSpot();
            parkingSpot.setParkingSpotSize(spotSize);
            parkingSpot.setSpotDescription(String.join("", floor, spotSize.name(),
                    String.valueOf(currentSpot)));
            parkingSpotList.add(parkingSpot);
        }
        return parkingSpotList;
    }


}
