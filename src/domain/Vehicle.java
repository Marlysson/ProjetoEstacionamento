package domain;

public class Vehicle {
	
	private String model;
	private String placa;
	
	public Vehicle(String model, String board){
		this.model = model;
		this.placa  = board;
	}
	
	public String getModel(){
		return this.model;
	}
	
	public String getBoard(){
		return this.placa;
	}
}
