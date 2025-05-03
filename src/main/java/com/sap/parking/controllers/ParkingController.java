package com.sap.parking.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.sap.parking.entities.Spot;
import com.sap.parking.entities.Zone;
import com.sap.parking.services.ParkingService;


@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    private final ParkingService service;

    public ParkingController(ParkingService service) {
        this.service = service;
    }

    /**
     * Park a car with given plate in the given zone.
     * Returns true on success, false if no free spot.
     */
    @PostMapping("/park")
    public boolean park(
            @RequestParam Integer plate,
            @RequestParam Zone zone
    ) {
        return service.park(plate, zone);
    }

    /** Un-park (leave) a car by plate. */
    @PostMapping("/leave")
    public boolean leave(@RequestParam Integer plate) {
        return service.leave(plate);
    }

    /** List all spots (occupiedBy column will show your parked plates) */
    @GetMapping("/spots")
    public List<Spot> listAll() {
        return service.listAll();
    }
}
