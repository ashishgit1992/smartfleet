package com.smartfleet.vehicle_service.repository;

import com.smartfleet.vehicle_service.model.Vehicle;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface VehicleRepository extends ReactiveCrudRepository<Vehicle,Long> {
    Mono<Vehicle> findByRegistrationNumber(String registrationNumber);
}
