package com.app.parkinglot.service.impl;

import com.app.parkinglot.exception.NotFoundException;
import com.app.parkinglot.models.dto.request.ParkingLotDTO;
import com.app.parkinglot.models.dto.response.ParkingLotResponseDTO;
import com.app.parkinglot.models.entity.ParkingLot;
import com.app.parkinglot.repository.ParkingLotRepository;
import com.app.parkinglot.service.AddressService;
import com.app.parkinglot.service.ParkingLotAttributeBuilder;
import com.app.parkinglot.service.ParkingLotService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ParkingLotServiceImpl implements ParkingLotService {
    private ParkingLotAttributeBuilder parkingLotAttributeBuilder;
    private AddressService addressService;

    private ParkingLotRepository parkingLotRepository;

    @Override
    public ParkingLotResponseDTO createParkingLot(ParkingLotDTO parkingLotDTO) {
        log.info("Parking lot creation started: {}", parkingLotDTO);
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setDescription(parkingLotDTO.getDescription());
        parkingLot.setAddress(addressService.getAddress(parkingLotDTO.getParkingLotAddressId()));
        parkingLot.setParkingFloors(parkingLotAttributeBuilder.buildParkingFloors(parkingLotDTO));
        parkingLotRepository.save(parkingLot);
        log.info("creation of parking lot finished: {}", parkingLot);
        return ParkingLot.to(parkingLot);
       // return parkingLot;
    }

    @Override
    public ParkingLot getParkingLotById(Long id) {
        Optional<ParkingLot> parkingLotOptional = parkingLotRepository.findById(id);
        if(parkingLotOptional.isEmpty()) {
            throw new NotFoundException("Parking Lot with ID " + id + " does not exist");
        }
        return parkingLotOptional.get();
    }
}
