package com.app.parkinglot.service.impl;

import com.app.parkinglot.exception.NotFoundException;
import com.app.parkinglot.models.entity.ParkingSpot;
import com.app.parkinglot.models.entity.Payment;
import com.app.parkinglot.models.entity.Ticket;
import com.app.parkinglot.models.enums.PaymentStatus;
import com.app.parkinglot.repository.PaymentRepository;
import com.app.parkinglot.repository.TicketRepository;
import com.app.parkinglot.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;

    private PaymentRepository paymentRepository;

    @Override
    public Ticket createTicket(ParkingSpot parkingSpot) {
        log.info("ticket creation for the parkingSpot: {}", parkingSpot);
        Ticket ticket = new Ticket();
        ticket.setEntryTime(System.currentTimeMillis());
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicleNumber(parkingSpot.getVehicleNumber());

        Payment payment = new Payment();
        payment.setPaymentStatus(PaymentStatus.PENDING);
        ticket.setPayment(payment);
        paymentRepository.save(payment);

        Ticket savedTicket = ticketRepository.save(ticket);
        log.info("created ticket: {} for parkingSpot: {}", ticket, parkingSpot);
        return savedTicket;
    }

    @Override
    public Ticket getTicketById(Long ticketId) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if(ticketOptional.isEmpty()) {
            log.error("Ticket with ID: {} not found in the system", ticketId );
            throw new NotFoundException("Ticket with ID " + ticketId + " does not exist");
        }
        return ticketOptional.get();
    }
}
