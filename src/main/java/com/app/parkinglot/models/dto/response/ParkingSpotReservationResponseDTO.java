package com.app.parkinglot.models.dto.response;

import com.app.parkinglot.models.enums.Size;

import java.math.BigDecimal;

public class ParkingSpotReservationResponseDTO {
    private String parkingSpotDescription;
    private Size spotSize;
    private Long ticketId;
    private BigDecimal calculatedPrice;
}
