package com.sap.parking.repos;

import com.sap.parking.entities.Handicapped;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandicappedRepository extends JpaRepository<Handicapped, Integer> { }
