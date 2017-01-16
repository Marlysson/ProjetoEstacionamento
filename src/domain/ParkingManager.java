package domain;

import java.util.List;

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

	public void registerEntry(Vehicle vehicle) throws VehicleAlreadyRegistered {
		
		if ( this.vehicleRegistered(vehicle) ){
			throw new VehicleAlreadyRegistered();
		}
		
		
		int firstPosition = this.getFirstFreePlace();
		
		this.vehicles[firstPosition] = vehicle;			
		
				
	}

	
	public Vehicle getByPosition(int i) throws IndexOutOfBoundsException {
		return this.vehicles[i-1];
	}
	
	private boolean vehicleRegistered(Vehicle vehicleToBeRegistered){
		
		for ( Vehicle vehicle : vehicles){
			
			if (vehicle != null){
				if ( vehicle.getBoard().equals(vehicleToBeRegistered.getBoard()) ){
					return true;
				}		
			}
		}
		
		return false;
		
	}
	
	private int getFirstFreePlace(){
		
		int i = 0;
		
		for (Vehicle vehicle : vehicles){
			if (vehicle == null){
				return i;
			}else{
				i++;				
			}
		}
		
		return i;
		
	}
		
	public Vehicle[] getVehicles(){
		return this.vehicles;
	}
	
}
