package com.app.parkinglot.controller;

import com.app.parkinglot.models.entity.Address;
import com.app.parkinglot.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/addresses")
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address createdAddress = addressService.createAddress(address);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> findAddressById(@PathVariable("id") String id) {
        Address retrievedAddress = addressService.getAddress(Long.valueOf(id));
        return new ResponseEntity<>(retrievedAddress, HttpStatus.OK);
    }
}
