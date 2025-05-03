package com.sap.parking.entities;

import javax.persistence.*;

@Entity
@Table(name = "spots")
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // avoid SQL keywords for column names
    @Column(name = "floor_index")
    private int floorIndex;

    @Column(name = "spot_index")
    private int spotIndex;

    @Enumerated(EnumType.STRING)
    private Zone zone;

    // null = free
    @Column(name = "occupied_by")
    private Integer occupiedBy;

    public Spot() { }

    public Spot(int floorIndex, int spotIndex, Zone zone) {
        this.floorIndex = floorIndex;
        this.spotIndex = spotIndex;
        this.zone = zone;
    }

    // --- getters & setters ---

    public Long getId() {
        return id;
    }

    public int getFloorIndex() {
        return floorIndex;
    }

    public void setFloorIndex(int floorIndex) {
        this.floorIndex = floorIndex;
    }

    public int getSpotIndex() {
        return spotIndex;
    }

    public void setSpotIndex(int spotIndex) {
        this.spotIndex = spotIndex;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Integer getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(Integer occupiedBy) {
        this.occupiedBy = occupiedBy;
    }

    @Override
    public String toString() {
        return "Spot{" +
                "id=" + id +
                ", floorIndex=" + floorIndex +
                ", spotIndex=" + spotIndex +
                ", zone=" + zone +
                ", occupiedBy=" + occupiedBy +
                '}';
    }
}
