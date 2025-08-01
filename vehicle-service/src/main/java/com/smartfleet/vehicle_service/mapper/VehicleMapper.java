package com.smartfleet.vehicle_service.mapper;


import com.smartfleet.vehicle_service.dto.VehicleRequestDTO;
import com.smartfleet.vehicle_service.dto.VehicleResponseDTO;
import com.smartfleet.vehicle_service.model.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public Vehicle toEntity(VehicleRequestDTO dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber(dto.getRegistrationNumber());
        vehicle.setOwnerName(dto.getOwnerName());
        vehicle.setModel(dto.getModel());
        vehicle.setManufacturer(dto.getManufacturer());
        vehicle.setYearOfManufacture(dto.getYearOfManufacture());
        vehicle.setFuelType(dto.getFuelType());
        vehicle.setVehicleType(dto.getVehicleType());
        return vehicle;
    }
    public VehicleResponseDTO toResponse(Vehicle vehicle) {
        VehicleResponseDTO response = new VehicleResponseDTO();
        response.setId(vehicle.getId());
        response.setRegistrationNumber(vehicle.getRegistrationNumber());
        response.setOwnerName(vehicle.getOwnerName());
        response.setModel(vehicle.getModel());
        response.setManufacturer(vehicle.getManufacturer());
        response.setYearOfManufacture(vehicle.getYearOfManufacture());
        response.setFuelType(vehicle.getFuelType());
        response.setVehicleType(vehicle.getVehicleType());
        return response;

    }

    public void merge(Vehicle incoming, Vehicle existing) {
        if (incoming.getRegistrationNumber() != null) {
            existing.setRegistrationNumber(incoming.getRegistrationNumber());
        }
        if (incoming.getOwnerName() != null) {
            existing.setOwnerName(incoming.getOwnerName());
        }
        if (incoming.getModel() != null) {
            existing.setModel(incoming.getModel());
        }
        if (incoming.getManufacturer() != null) {
            existing.setManufacturer(incoming.getManufacturer());
        }
        if (incoming.getYearOfManufacture() != null) {
            existing.setYearOfManufacture(incoming.getYearOfManufacture());
        }
        if (incoming.getFuelType() != null) {
            existing.setFuelType(incoming.getFuelType());
        }
        if (incoming.getVehicleType() != null) {
            existing.setVehicleType(incoming.getVehicleType());
        }
    }

}