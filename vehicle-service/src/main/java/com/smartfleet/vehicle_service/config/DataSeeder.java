package com.smartfleet.vehicle_service.config;

import com.smartfleet.vehicle_service.model.Vehicle;
import com.smartfleet.vehicle_service.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class DataSeeder {

    private final VehicleRepository vehicleRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void seedData() {
        vehicleRepository.deleteAll()
                .thenMany(
                        Flux.just(
                                createVehicle("DL01AX1234", "Ashish Bhaukali", "Scorpio", "Mahindra", 2022, "Diesel", "SUV"),
                                createVehicle("DL01BA5678", "Tiwari Ji", "Fortuner", "Toyota", 2023, "Diesel", "SUV")
                        ).flatMap(vehicleRepository::save)
                )
                .subscribe();

        System.out.println("🚀 Seed data inserted!");
    }

    private Vehicle createVehicle(String reg, String owner, String model, String brand, int year, String fuel, String type) {
        Vehicle v = new Vehicle();
        v.setRegistrationNumber(reg);
        v.setOwnerName(owner);
        v.setModel(model);
        v.setManufacturer(brand);
        v.setYearOfManufacture(year);
        v.setFuelType(fuel);
        v.setVehicleType(type);
        return v;
    }
}
