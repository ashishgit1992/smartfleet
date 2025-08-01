package com.smartfleet.vehicle_service.service;

import com.smartfleet.vehicle_service.dto.VehicleRequestDTO;
import com.smartfleet.vehicle_service.dto.VehicleResponseDTO;
import com.smartfleet.vehicle_service.model.Vehicle;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VehicleService {

    Mono<VehicleResponseDTO> createVehicle(VehicleRequestDTO dto);
    Mono<VehicleResponseDTO> getVehicleById(Long id);
    Flux<VehicleResponseDTO> getAllVehicles();
    Mono<VehicleResponseDTO> updateVehicle(Long id,VehicleRequestDTO dto);
    Mono<Void> deleteVehicle(Long id);
    Mono<VehicleResponseDTO> getVehicleByRegistrationNumber(String registrationNumber);
}
