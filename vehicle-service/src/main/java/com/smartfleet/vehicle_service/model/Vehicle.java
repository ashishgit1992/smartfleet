package com.smartfleet.vehicle_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("vehicles") // 🧠 DB Table name
public class Vehicle {

    @Id
    private Long id;

    @Column("registration_number")
    private String registrationNumber;

    @Column("owner_name")
    private String ownerName;

    @Column("model")
    private String model;

    @Column("manufacturer")
    private String manufacturer;

    @Column("year_of_manufacture")
    private Integer yearOfManufacture;

    @Column("fuel_type")
    private String fuelType;

    @Column("vehicle_type")
    private String vehicleType;
}
