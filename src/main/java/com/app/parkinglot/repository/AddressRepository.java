package com.app.parkinglot.repository;

import com.app.parkinglot.models.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
