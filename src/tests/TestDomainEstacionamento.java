package tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.ParkingManager;
import domain.Vehicle;
import exceptions.ParkingVehicleFullException;
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
	public void testDeveGerarUmErroQuandoRegistrarUmCarroJaRegistrado() throws VehicleAlreadyRegistered, ParkingVehicleFullException{
		
		Vehicle vehicle_1 = new Vehicle("Fusion","200-2RR3");
		Vehicle vehicle_2 = new Vehicle("Fusion","200-2RR3");
		
		manager.registerEntry(vehicle_1);
		manager.registerEntry(vehicle_2);
	}
	
	@Test
	public void testDeveEstacionarVeiculoNaPrimeiraVagaDisponivel() throws VehicleAlreadyRegistered, ParkingVehicleFullException{
		
		Vehicle vehicle_1 = new Vehicle("Fusion","200-2RR5");
		Vehicle vehicle_2 = new Vehicle("Fusion","200-2RR4");
		
		manager.registerEntry(vehicle_1);
		manager.registerEntry(vehicle_2);
		
		Vehicle vehicleFirstPosition = manager.getByPosition(1);
		assertEquals(vehicleFirstPosition.getBoard(), vehicle_1.getBoard());
		
		Vehicle vehicleSecondPosition = manager.getByPosition(2);
		assertEquals(vehicleSecondPosition.getBoard(), vehicle_2.getBoard());
				
	}
	
	@Test(expected=ParkingVehicleFullException.class)
	public void TestNaoDeveMaisAceitarVeiculosComEstacionamentoCheio() throws VehicleAlreadyRegistered, ParkingVehicleFullException{
		
		List<String> boards = Arrays.asList("200-0001","200-0002","200-0003",
										"200-0004","200-0005","200-0006",
										"200-0007","200-0008","200-0009",
										"200-0010");
		
		for( String board : boards){
			Vehicle vehicle = new Vehicle("Ford Fusion",board);
			manager.registerEntry(vehicle);
		}
		
		Vehicle vehicleNotAccepted = new Vehicle("Ford Fusion","200-0011");
		manager.registerEntry(vehicleNotAccepted);
	}

}
