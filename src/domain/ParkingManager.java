package domain;

import exceptions.VehicleAlreadyRegistered;
import exceptions.ParkingVehicleFullException;


public class ParkingManager {
	
	private static ParkingManager instance;
	
	private static final int maxPlaces = 10;
			
	private Vehicle[] vehicles = new Vehicle[maxPlaces];
	
	public static ParkingManager getInstance() {
		
		if ( instance == null ){
			instance = new ParkingManager();
		}
		
		return instance;
	}

	public void registerEntry(Vehicle vehicle) throws VehicleAlreadyRegistered, ParkingVehicleFullException {
		
		if ( this.vehicleRegistered(vehicle) ){
			throw new VehicleAlreadyRegistered();
		}
		
		if(this.parkinkIsFull()){
			throw new ParkingVehicleFullException();
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
	
	private boolean parkinkIsFull(){
		
		int numberOfPlaces = 0;
		
		for(Vehicle vehicle : vehicles){
			
			if (vehicle != null){
				numberOfPlaces++;
			}
		}
		
		if (numberOfPlaces == this.getNumVehicles()){
			return true;
		}
		
		return false;
		
		
	}
	
	private int getNumVehicles(){
		return this.vehicles.length;
	}
	
	
	public Vehicle[] getVehicles(){
		return this.vehicles;
	}
	
}
