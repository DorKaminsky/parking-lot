package com.sap.parking.repos;

import java.util.List;
import com.sap.parking.entities.Spot;
import com.sap.parking.entities.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, Long> {
    List<Spot> findByOccupiedByIsNullAndZone(Zone zone);
}
