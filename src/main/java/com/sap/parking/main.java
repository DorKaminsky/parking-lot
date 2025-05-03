package com.sap.parking;

public class main {

	public static void main(String[] args) {
		ParkingLot parkingLot = new ParkingLot(3, 20);

		for (int plateNumber = 1000; plateNumber < 1010; plateNumber++) {
		    parkingLot.addHandicappedPlate(plateNumber);
		}

		for (int plateNumber = 2000; plateNumber < 2020; plateNumber++) {
		    parkingLot.park(plateNumber);
		}

		parkingLot.displayFreeSpots();

		for (int plateNumber = 2020; plateNumber < 2060; plateNumber++) {
		    parkingLot.park(plateNumber);
		}

		for (int plateNumber = 1000; plateNumber < 1010; plateNumber++) {
		    parkingLot.park(plateNumber);
		}

		parkingLot.leaveParking(1004);
		parkingLot.leaveParking(2010);
		parkingLot.leaveParking(2020);
		parkingLot.leaveParking(2030);

		for (int plateNumber = 1006; plateNumber < 1010; plateNumber++) {
		    parkingLot.park(plateNumber);
		}
		

	}

}
