package com.smartfleet.vehicle_service.controller;

import com.smartfleet.vehicle_service.dto.VehicleRequestDTO;
import com.smartfleet.vehicle_service.dto.VehicleResponseDTO;
import com.smartfleet.vehicle_service.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<VehicleResponseDTO> createVehicle(@RequestBody VehicleRequestDTO dto) {
        return vehicleService.createVehicle(dto);
    }

    @GetMapping("/{id}")
    public Mono<VehicleResponseDTO> getVehicleById(@PathVariable(name = "id") Long id) {
        return vehicleService.getVehicleById(id);
    }

    @GetMapping("/search")
    public Mono<VehicleResponseDTO> getVehicleById(@RequestParam("reg") String registrationNumber) {
        return vehicleService.getVehicleByRegistrationNumber(registrationNumber);
    }


    @GetMapping
    public Flux<VehicleResponseDTO> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @PutMapping("{id}")
    public Mono<VehicleResponseDTO> updateVehicle(@PathVariable(name = "id") long id, @RequestBody VehicleRequestDTO dto) {
        return vehicleService.updateVehicle(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteVehicle(@PathVariable(name = "id") long id) {
       return vehicleService.deleteVehicle(id);
    }
}
