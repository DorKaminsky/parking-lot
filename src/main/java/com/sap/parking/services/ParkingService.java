package com.sap.parking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.parking.entities.Handicapped;
import com.sap.parking.entities.Spot;
import com.sap.parking.entities.Zone;
import com.sap.parking.repos.HandicappedRepository;
import com.sap.parking.repos.SpotRepository;

@Service
public class ParkingService {

    private final SpotRepository spotRepo;
    private final HandicappedRepository hcRepo;

    public ParkingService(SpotRepository spotRepo, HandicappedRepository hcRepo) {
        this.spotRepo = spotRepo;
        this.hcRepo = hcRepo;
    }

    /** New factory method for initializing spots */
    @Transactional
    public Spot createSpot(int floorIndex, int spotIndex, Zone zone) {
        Spot spot = new Spot(floorIndex, spotIndex, zone);
        return spotRepo.save(spot);
    }

    @Transactional
    public boolean park(Integer plate, Zone requestedZone) {
        boolean isHc = hcRepo.existsById(plate);
        Zone zone = isHc ? Zone.BLUE : requestedZone;

        List<Spot> free = spotRepo.findByOccupiedByIsNullAndZone(zone);
        if (free.isEmpty()) return false;

        Spot spot = free.get(0);
        spot.setOccupiedBy(plate);
        spotRepo.save(spot);
        return true;
    }

    @Transactional
    public boolean leave(Integer plate) {
        Optional<Spot> occupied = spotRepo.findAll()
                .stream()
                .filter(s -> plate.equals(s.getOccupiedBy()))
                .findFirst();

        if (occupied.isEmpty()) return false;
        Spot spot = occupied.get();
        spot.setOccupiedBy(null);
        spotRepo.save(spot);
        return true;
    }

    @Transactional
    public void addHandicapped(Integer plate) {
        hcRepo.save(new Handicapped(plate));
    }

    public List<Spot> listAll() {
        return spotRepo.findAll();
    }
}
