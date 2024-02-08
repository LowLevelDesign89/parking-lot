package com.app.parkinglot.repository;

import com.app.parkinglot.models.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
