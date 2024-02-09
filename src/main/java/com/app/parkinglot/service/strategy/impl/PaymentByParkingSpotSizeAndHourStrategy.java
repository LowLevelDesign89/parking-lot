package com.app.parkinglot.service.strategy.impl;

import com.app.parkinglot.models.entity.Ticket;
import com.app.parkinglot.models.enums.Size;
import com.app.parkinglot.service.strategy.PaymentStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PaymentByParkingSpotSizeAndHourStrategy implements PaymentStrategy {
    private final BigDecimal largeSpotPerHour = BigDecimal.valueOf(200);
    private final BigDecimal mediumSpotPerHour = BigDecimal.valueOf(150);
    private final BigDecimal smallSpotPerHour = BigDecimal.valueOf(100);


    @Override
    public void calculatePayment(Ticket ticket) {
        log.info("price calculation started for ticket: {}", ticket);
        BigDecimal finalPrice = null;
        if(ticket.getParkingSpot().getParkingSpotSize().equals(Size.LARGE)) {
            finalPrice = largeSpotPerHour;
        } else if(ticket.getParkingSpot().getParkingSpotSize().equals(Size.MEDIUM)) {
            finalPrice = mediumSpotPerHour;
        } else {
            finalPrice = smallSpotPerHour;
        }

        float totalTimeElapsed =(float) ((ticket.getExitTime() - ticket.getEntryTime()) / (1000.0 * 3600));
        finalPrice = finalPrice.multiply(BigDecimal.valueOf(totalTimeElapsed));
        log.info("final price for the parkingSpot: {} is {}", ticket.getParkingSpot(), finalPrice);
        ticket.getPayment().setPrice(finalPrice);
    }
}
