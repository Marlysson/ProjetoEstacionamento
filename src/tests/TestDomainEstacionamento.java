package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import domain.ParkingManager;
import domain.Vehicle;
import exceptions.VehicleAlreadyRegistered;

public class TestDomainEstacionamento {
	
	@Test
	public void testDeveRetornarUmSingletonDaClasse(){
		
		ParkingManager manager_1 = ParkingManager.getInstance();
		ParkingManager manager_2 = ParkingManager.getInstance();
		
		assertEquals(manager_1,manager_2);
	}
	
	@Test
	public void testDeveRetornarOsDadosCorretosDoVeiculo(){
		
		Vehicle vehicle = new Vehicle("Fusion","X33-2030");
		
		assertEquals(vehicle.getModel(),"Fusion");
		assertEquals(vehicle.getBoard(),"X33-2030");
	}
	
	@Test
	public void testDeveRegistrarVeiculoNaPrimeiraVaga() throws VehicleAlreadyRegistered{
		
		Vehicle vehicle = new Vehicle("Fusion","200-2RR3");
	
		ParkingManager manager = ParkingManager.getInstance();
	
		manager.registerEntry(vehicle);
		
		Vehicle vehicleInto = manager.getByPosition(1);
		
		assertEquals(vehicle,vehicleInto);
	}
	
	@Test
	public void testDeveGerarUmErroQuandoRegistrarUmCarroJaRegistrado() throws VehicleAlreadyRegistered{
		
		ParkingManager manager = ParkingManager.getInstance();
		
		Vehicle vehicle_1 = new Vehicle("Fusion","200-2RR3");
		Vehicle vehicle_2 = new Vehicle("Fusion","200-2RR3");
		
		manager.registerEntry(vehicle_1);
		manager.registerEntry(vehicle_2);
	}

}
