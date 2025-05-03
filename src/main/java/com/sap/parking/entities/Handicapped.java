package com.sap.parking.entities;

import javax.persistence.*;

@Entity
@Table(name = "handicapped_plates")
public class Handicapped {
    @Id
    private Integer plateNumber;

    public Handicapped() {}
    public Handicapped(Integer plateNumber) {
        this.plateNumber = plateNumber;
    }
    // Getter/setter...
}
