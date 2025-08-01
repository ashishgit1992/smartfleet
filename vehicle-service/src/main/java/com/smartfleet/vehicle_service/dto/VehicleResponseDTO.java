package com.smartfleet.vehicle_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDTO {
    private Long id;
    private String registrationNumber;
    private String ownerName;
    private String model;
    private String manufacturer;
    private int yearOfManufacture;
    private String fuelType;
    private String vehicleType;
}
