package com.sap.parking;

public class ParkingSpot {
	 enum State{
		FREE,
		OCCUPIED
	}
	
	 enum Zone{
		BLUE,
		GREEN,
		ORANGE,
		RED
	}
	 
	 private int spotID;
	 private int plateInSpot;
	 private State state;
	 private Zone zone;
	 
	 public ParkingSpot(int numOfFloor,int spotOnFloor,int zone) {
			super();
			this.setState(State.FREE);
			this.setSpotID(numOfFloor,spotOnFloor);
			this.setZone(Zone.values()[zone]);
		}

	public int getSpotID() {
		return spotID;
	}

	public void setSpotID(int floor,int spot) {
		this.spotID = (floor*100) + spot;
	}

	public int getPlateInSpot() {
		return plateInSpot;
	}

	public void setPlateInSpot(int plateInSpot) {
		this.plateInSpot = plateInSpot;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public void parkInSpot(int plateNumber) {
		this.setPlateInSpot(plateNumber);
		this.setState(State.OCCUPIED);
	}
	
	@Override
	public String toString() {
		return "Plate "+plateInSpot+ "|spot " + spotID % 100 + "| floor " + spotID/100 + "| zone " + zone + ". ";
	}

	public void evacuateSpot() {
		this.plateInSpot=-1;
		this.setState(State.FREE);
	}

}
