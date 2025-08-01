package com.smartfleet.vehicle_service;

import com.smartfleet.vehicle_service.repository.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories(basePackages = "com.smartfleet.vehicle_service.repository")
public class VehicleServiceApplication {
	@Bean
	CommandLineRunner test(VehicleRepository repo) {
		return args -> {
			System.out.println("-------------------------------------Repo Injected: " + repo);
		};
	}


	public static void main(String[] args) {
		SpringApplication.run(VehicleServiceApplication.class, args);
	}

}
