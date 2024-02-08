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
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;

    private PaymentRepository paymentRepository;

    @Override
    public Ticket createTicket(ParkingSpot parkingSpot) {
        Ticket ticket = new Ticket();
        ticket.setEntryTime(System.currentTimeMillis());
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicleNumber(parkingSpot.getVehicleNumber());

        Payment payment = new Payment();
        payment.setPaymentStatus(PaymentStatus.PENDING);
        ticket.setPayment(payment);
        paymentRepository.save(payment);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicketById(Long ticketId) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if(ticketOptional.isEmpty()) {
            throw new NotFoundException("Ticket with ID " + ticketId + " does not exist");
        }
        return ticketOptional.get();
    }
}
