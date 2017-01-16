package domain;

public class Vehicle {
	
	private String model;
	private String board;
	
	public Vehicle(String model, String board){
		this.model = model;
		this.board  = board;
	}
	
	public String getModel(){
		return this.model;
	}
	
	public String getBoard(){
		return this.board;
	}
	
	public String toString(){
		return this.model+" / "+this.board;
	}
}
