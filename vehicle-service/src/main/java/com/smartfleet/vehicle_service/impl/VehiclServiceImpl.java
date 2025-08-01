package com.smartfleet.vehicle_service.impl;

import com.smartfleet.vehicle_service.dto.VehicleRequestDTO;
import com.smartfleet.vehicle_service.dto.VehicleResponseDTO;
import com.smartfleet.vehicle_service.mapper.VehicleMapper;
import com.smartfleet.vehicle_service.model.Vehicle;
import com.smartfleet.vehicle_service.repository.VehicleRepository;
import com.smartfleet.vehicle_service.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class VehiclServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private static final Logger log = LoggerFactory.getLogger(VehiclServiceImpl.class);

    @Override
    public Mono<VehicleResponseDTO> createVehicle(VehicleRequestDTO dto) {
        Vehicle vehicle = vehicleMapper.toEntity(dto);
        log.info("Saving vehicle with registration {}", dto.getRegistrationNumber());
        log.debug("Vehicle saved: {}", vehicle);

        return mapToMonoResponse(vehicleRepository.save(vehicle));
    }

    @Override
    public Mono<VehicleResponseDTO> getVehicleById(Long id) {
        return mapToMonoResponse(vehicleRepository.findById(id));
    }

    public Mono<VehicleResponseDTO> getVehicleByRegistrationNumber(String registrationNumber) {
        return mapToMonoResponse(vehicleRepository.findByRegistrationNumber(registrationNumber));
    }

    @Override
    public Flux<VehicleResponseDTO> getAllVehicles() {
        return mapToFluxResponse(vehicleRepository.findAll());
    }

    @Override
    public Mono<VehicleResponseDTO> updateVehicle(Long id, VehicleRequestDTO dto) {
        return mapToMonoResponse(
                vehicleRepository.findById(id)
                        .flatMap(existing -> {
                            Vehicle incoming = vehicleMapper.toEntity(dto);
                            vehicleMapper.merge(incoming, existing);
                            return vehicleRepository.save(existing);
                        }));
    }

    @Override
    public Mono<Void> deleteVehicle(Long id) {
        return vehicleRepository.deleteById(id);
    }

    private Mono<VehicleResponseDTO> mapToMonoResponse(Mono<Vehicle> mono) {
        return mono.map(vehicleMapper::toResponse);
    }

    private Flux<VehicleResponseDTO> mapToFluxResponse(Flux<Vehicle> flux) {
        return flux.map(vehicleMapper::toResponse);
    }
}
