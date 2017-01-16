package domain;

import exceptions.ParkingVehicleFullException;
import exceptions.VehicleAlreadyRegistered;
import exceptions.VehicleNotRegisteredException;


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
	
	public void registerOut(Vehicle vehicle) throws VehicleNotRegisteredException{
		
		if (!this.vehicleRegistered(vehicle)){
			throw new VehicleNotRegisteredException();
		}
		
		int positionOuted = this.getPositionByBoard(vehicle.getBoard());
		
		this.vehicles[positionOuted] = null;
		
	}

	
	public Vehicle getByPosition(int i) throws IndexOutOfBoundsException {
		return this.vehicles[i-1];
	}
	
	public int getPositionByBoard(String board){
		
		for( int i = 0; i < this.getNumVehicles(); i++){
			if (this.vehicles[i] != null){

				if (this.vehicles[i].getBoard().equals(board)){
					return i;
				}
				
			}
		}
		
		return -1;
		
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
				break;
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
