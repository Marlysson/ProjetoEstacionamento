package tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.ParkingManager;
import domain.Vehicle;
import exceptions.VehicleAlreadyRegistered;

public class TestDomainEstacionamento {
		

	ParkingManager manager;
	
	@Before
	public void carregaManager(){
		
		manager = ParkingManager.getInstance();
		
	}
	
	@After
	public void resetandoVagasDoEstacionameto(){
		for(int i = 0; i < manager.getVehicles().length ; i++){
			manager.getVehicles()[i] = null;
		}
	}
	
	
	@Test
	public void testDeveRetornarUmSingletonDaClasse(){
		
		ParkingManager new_manager = ParkingManager.getInstance();
		
		assertEquals(manager,new_manager);
	}
	
	@Test
	public void testDeveRetornarOsDadosCorretosDoVeiculo(){
		
		Vehicle vehicle = new Vehicle("Fusion","X33-2030");
		
		assertEquals(vehicle.getModel(),"Fusion");
		assertEquals(vehicle.getBoard(),"X33-2030");
	}
	
	@Test(expected=VehicleAlreadyRegistered.class)
	public void testDeveGerarUmErroQuandoRegistrarUmCarroJaRegistrado() throws VehicleAlreadyRegistered{
		
		Vehicle vehicle_1 = new Vehicle("Fusion","200-2RR3");
		Vehicle vehicle_2 = new Vehicle("Fusion","200-2RR3");
		
		manager.registerEntry(vehicle_1);
		manager.registerEntry(vehicle_2);
	}
	
	@Test
	public void testDeveEstacionarVeiculoNaPrimeiraVagaDisponivel() throws VehicleAlreadyRegistered{
		
		Vehicle vehicle_1 = new Vehicle("Fusion","200-2RR5");
		Vehicle vehicle_2 = new Vehicle("Fusion","200-2RR4");
		
		manager.registerEntry(vehicle_1);
		manager.registerEntry(vehicle_2);
		
		Vehicle vehicleFirstPosition = manager.getByPosition(1);
		assertEquals(vehicleFirstPosition.getBoard(), vehicle_1.getBoard());
		
		Vehicle vehicleSecondPosition = manager.getByPosition(2);
		assertEquals(vehicleSecondPosition.getBoard(), vehicle_2.getBoard());
				
	}

}
