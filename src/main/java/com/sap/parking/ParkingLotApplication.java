package com.sap.parking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sap.parking.entities.Spot;
import com.sap.parking.entities.Zone;
import com.sap.parking.services.ParkingService;

@SpringBootApplication
public class ParkingLotApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParkingLotApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(ParkingService service) {
        return args -> {
            // Initialize some spots if DB is empty
            if (service.listAll().isEmpty()) {
                for (int f = 0; f < 2; f++) {
                    for (int i = 0; i < 5; i++) {
                        service.createSpot(f, i, Zone.BLUE);
                    }
                }
            }
            service.addHandicapped(1234);
            System.out.println("Park 1234 in RED → " + service.park(1234, Zone.RED));
            System.out.println("Park 9999 in GREEN → " + service.park(9999, Zone.GREEN));
            service.leave(1234);
            System.out.println("Final spots:");
            service.listAll().forEach(System.out::println);
        };
    }
}

