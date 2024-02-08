package com.app.parkinglot.models.dto.request;

import com.app.parkinglot.models.enums.PaymentMethod;
import lombok.Data;

@Data
public class ParkingSpotDeallocationRequestDTO {
    private Long ticketId;
    private PaymentMethod paymentMethod;
}
