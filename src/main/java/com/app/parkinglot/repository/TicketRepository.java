package com.app.parkinglot.repository;

import com.app.parkinglot.models.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
