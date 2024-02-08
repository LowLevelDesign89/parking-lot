package com.app.parkinglot.service;

import com.app.parkinglot.models.entity.Address;

public interface AddressService {
    Address createAddress(Address address);

    Address getAddress(Long id);
}
