package com.app.parkinglot.service;

import com.app.parkinglot.models.entity.ParkingSpot;
import com.app.parkinglot.models.entity.Ticket;

public interface TicketService {
    Ticket createTicket(ParkingSpot parkingSpot);

    Ticket getTicketById(Long ticketId);
}
