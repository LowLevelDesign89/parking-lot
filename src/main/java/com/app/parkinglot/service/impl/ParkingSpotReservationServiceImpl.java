package com.app.parkinglot.service.impl;

import com.app.parkinglot.exception.InvalidRequestException;
import com.app.parkinglot.exception.NotAvailableException;
import com.app.parkinglot.models.dto.request.ParkingSpotDeallocationRequestDTO;
import com.app.parkinglot.models.dto.request.VehicleDTO;
import com.app.parkinglot.models.entity.ParkingSpot;
import com.app.parkinglot.models.entity.Ticket;
import com.app.parkinglot.models.enums.PaymentStatus;
import com.app.parkinglot.repository.ParkingSpotRepository;
import com.app.parkinglot.repository.TicketRepository;
import com.app.parkinglot.service.ParkingSpotReservationService;
import com.app.parkinglot.service.TicketService;
import com.app.parkinglot.service.strategy.PaymentStrategy;
import com.app.parkinglot.util.AppConstants;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ParkingSpotReservationServiceImpl implements ParkingSpotReservationService {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private TicketService ticketService;

    @Resource
    private Map<String, PaymentStrategy> paymentStrategyMap;
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public ParkingSpot allocateSpotToVehicle(VehicleDTO vehicleDTO) {
        Optional<ParkingSpot> parkingSpotOptional = parkingSpotRepository
                .findByVehicleNumberAndParkingSpotSize(vehicleDTO.getRegistrationNumber(), vehicleDTO.getSize());
        if(parkingSpotOptional.isPresent()) {
            throw new InvalidRequestException("Vehicle with registration number " + vehicleDTO.getRegistrationNumber() + "" +
                    " already parked at " + parkingSpotOptional.get().getSpotDescription() + ", can make another request");
        }
        List<ParkingSpot> availableParkingSpots = parkingSpotRepository
                .findByParkingSpotSizeAndVehicleNumberIsNull(vehicleDTO.getSize());
        if(availableParkingSpots.isEmpty()) {
            throw new NotAvailableException("No parking spot available for vehicle with size " + vehicleDTO.getSize());
        }
        availableParkingSpots.sort(Comparator.comparing(ParkingSpot::getSpotDescription));
        ParkingSpot firstSpot = availableParkingSpots.get(0);
        firstSpot.setVehicleNumber(vehicleDTO.getRegistrationNumber());
        parkingSpotRepository.save(firstSpot);
        ticketService.createTicket(firstSpot);
        return firstSpot;
    }

    @Override
    public Ticket deallocateParkingSpot(ParkingSpotDeallocationRequestDTO requestDTO) {
        Ticket ticket = ticketService.getTicketById(requestDTO.getTicketId());
        ticket.setExitTime(System.currentTimeMillis());
        PaymentStrategy paymentStrategy = paymentStrategyMap.get(AppConstants.PAYMENT_STRATEGY_BY_HOUR_AND_SPOT_SIZE);
        paymentStrategy.calculatePayment(ticket);
        ticket.getPayment().setPaymentStatus(PaymentStatus.SUCCESS);
        ticket.getPayment().setPaymentMethod(requestDTO.getPaymentMethod());

        ParkingSpot parkingSpot = ticket.getParkingSpot();
        parkingSpot.setVehicleNumber(null);
        ticketRepository.save(ticket);
        parkingSpotRepository.save(parkingSpot);
        return ticket;
    }
}
