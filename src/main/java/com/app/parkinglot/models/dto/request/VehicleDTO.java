package com.app.parkinglot.models.dto.request;

import com.app.parkinglot.models.enums.Size;
import lombok.Data;

@Data
public class VehicleDTO {
    private String registrationNumber;
    private Size size;
}
