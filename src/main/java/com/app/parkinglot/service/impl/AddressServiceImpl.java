package com.app.parkinglot.service.impl;

import com.app.parkinglot.exception.NotFoundException;
import com.app.parkinglot.models.entity.Address;
import com.app.parkinglot.repository.AddressRepository;
import com.app.parkinglot.service.AddressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public Address createAddress(Address address) {
        log.info("Address creation process for the address: {}", address);
        Address createdAddress = addressRepository.save(address);

        log.info("Address created with ID: {}", createdAddress.getId());
        return createdAddress;
    }

    @Override
    public Address getAddress(Long id) {
        log.info("Started fetching address for the ID: {}", id);
        Optional<Address> retrievedAddress = addressRepository.findById(id);
        if(retrievedAddress.isEmpty()) {
            throw new NotFoundException("Address with ID " + id + " not exists");
        }
        log.info("Address retrieved: {}", retrievedAddress);
        return retrievedAddress.get();
    }
}
