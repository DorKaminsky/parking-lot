package com.sap.parking;

import java.util.ArrayList;
import java.util.HashMap;

public class ParkingLot {
	
	private int numOfFloors;
	private int spotsPerFloor;
	private int blue;
	private int green;
	private int orange;
	private int[] floorsCounter;
	private ArrayList<Integer> handicappPLates;
	private ParkingSpot[][] parkingMat;
	private HashMap<Integer,ParkingSpot> spotsOfPlates;
	
	

	public ParkingLot(int floors, int spots) {
		setNumOfFloors(floors);
		setSpotsPerFloor(spots);
		setHandicappPLates();
		setBlue();
		setGreen();
		setOrange();
		setParkingMat();
		setSpotsOfPlates();
		this.setFloorsCounter();
	}

	public void addHandicappedPlate(int plateNumber) {
		this.getHandicappPLates().add(plateNumber);
	}

	public void displayFreeSpots() {
		System.out.println("Free spots:\n--------");
		for (int i = 0 ;i < this.getNumOfFloors(); i++) {
			System.out.println("floor no. "+ i + ": "+ this.getFloorsCounter()[i]);
		}
		
	}

	public void park(int plateNumber) {
		for (Integer x : this.getHandicappPLates()) {
			if (x.intValue()==plateNumber) {
				int parked = handicappPark(plateNumber);
				if (parked==1) {
					return;
				}
				
			}
		}
		if (regularPark(plateNumber,this.getSpotsPerFloor()/10,3*this.getSpotsPerFloor()/10)==0) {
			if (regularPark(plateNumber,3*this.getSpotsPerFloor()/10,6*this.getSpotsPerFloor()/10)==0) {
				if (regularPark(plateNumber,6*this.getSpotsPerFloor()/10,getSpotsPerFloor())==0) {
					System.out.println("Can't find free parking spot for plate "+plateNumber);
				}
			}
		}
		
		
	}
	
	public int regularPark(int plateNumber,int start,int end) {
		for (int i = 0 ;i < this.getNumOfFloors(); i++) {
			for (int j = start ; j < end; j++) {
				if (this.getParkingMat()[i][j].getState().ordinal()==0) {
					this.getParkingMat()[i][j].parkInSpot(plateNumber);
					this.getSpotsOfPlates().put(plateNumber,this.getParkingMat()[i][j]);
					this.getFloorsCounter()[i]--;
					System.out.println("-- Parked successfully ---"+this.getParkingMat()[i][j] + "Remaining free spots: "+this.getFloorsCounter()[i]);
					return 1;
				}
			}
		}
		return 0;
	}

	public int handicappPark(int plateNumber) {
		for (int i = 0 ;i < this.getNumOfFloors(); i++) {
			for (int j = 0 ; j < this.getSpotsPerFloor()/10; j++) {
				if (this.getParkingMat()[i][j].getState().ordinal()==0) {
					this.getSpotsOfPlates().put(plateNumber,this.getParkingMat()[i][j]);
					this.getParkingMat()[i][j].parkInSpot(plateNumber);
					this.getFloorsCounter()[i]--;
					System.out.println("-- Parked successfully ---"+this.getParkingMat()[i][j] + "Remaining free spots: "+this.getFloorsCounter()[i] );
					return 1;
				}
			}
		}
		return 0;		
	}

	public void leaveParking(int plate) {
		ParkingSpot spot= this.getSpotsOfPlates().get(plate);
		int floor = spot.getSpotID()/100;
		this.getFloorsCounter()[floor]++;
		spot.evacuateSpot();
		this.getSpotsOfPlates().remove(plate);
		System.out.println("*** Leaving *** "+ spot + "Remaining free spots: "+this.getFloorsCounter()[floor] );
	}

	public int getNumOfFloors() {
		return numOfFloors;
	}

	public void setNumOfFloors(int numOfFloors) {
		this.numOfFloors = numOfFloors;
	}

	public int getSpotsPerFloor() {
		return spotsPerFloor;
	}

	public void setSpotsPerFloor(int spotsPerFloor) {
		this.spotsPerFloor = spotsPerFloor;
	}

	public ParkingSpot[][] getParkingMat() {
		return parkingMat;
	}

	public void setParkingMat() {
		this.parkingMat = new ParkingSpot[this.getNumOfFloors()][this.getSpotsPerFloor()];
		int zone = 0;
		this.blue = this.getSpotsPerFloor()/10;
		this.green = 3 * this.blue;
		this.orange= 2 * this.green;
		for (int i = 0 ;i < this.getNumOfFloors(); i++) {
			for (int j = 0 ; j < this.getSpotsPerFloor(); j++) {
				if (j < this.blue) {zone = 0;}	
				else  if (j < this.green) {zone = 1;}
				else if (j<this.orange) {zone =2;}
				else {zone = 3;}
				this.getParkingMat()[i][j] = new ParkingSpot(i,j,zone);
			}
		}
	}

	public ArrayList<Integer> getHandicappPLates() {
		return handicappPLates;
	}

	public void setHandicappPLates() {
		this.handicappPLates = new ArrayList<Integer>();
	}

	public HashMap<Integer,ParkingSpot> getSpotsOfPlates() {
		return spotsOfPlates;
	}

	public void setSpotsOfPlates() {
		this.spotsOfPlates = new HashMap<Integer,ParkingSpot>();
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue() {
		this.blue = this.getSpotsPerFloor()/10;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen() {
		this.green = blue*3;
	}

	public int getOrange() {
		return orange;
	}

	public void setOrange() {
		this.orange = blue*6;
	}

	public int[] getFloorsCounter() {
		return floorsCounter;
	}

	public void setFloorsCounter() {
		this.floorsCounter = new int[this.getNumOfFloors()];
		for (int i=0 ; i < floorsCounter.length;i++) {
			floorsCounter[i] = this.getSpotsPerFloor();
		}
	}

}
