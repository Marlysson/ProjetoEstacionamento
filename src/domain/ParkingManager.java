package domain;

import exceptions.VehicleAlreadyRegistered;

public class ParkingManager {
	
	private static ParkingManager instance;
	
	private Vehicle[] vehicles = new Vehicle[10];
	private Integer position = 0;
	
	public static ParkingManager getInstance() {
		
		if ( instance == null ){
			instance = new ParkingManager();
		}
		
		return instance;
	}

	public void registerEntry(Vehicle vehicle) {
		this.vehicles[position] = vehicle;
		
		position++;
	}

	public Vehicle getByPosition(int i) throws IndexOutOfBoundsException {
		return this.vehicles[i-1];
	}
	
	private boolean verifyVehicle(Vehicle vehicle) throws VehicleAlreadyRegistered{
		throw new VehicleAlreadyRegistered();
		
	}
	
}
