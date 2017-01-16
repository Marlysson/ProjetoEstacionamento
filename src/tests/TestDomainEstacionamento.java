package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.ParkingManager;
import domain.Vehicle;
import exceptions.ParkingVehicleFullException;
import exceptions.VehicleAlreadyRegistered;
import exceptions.VehicleNotRegisteredException;

public class TestDomainEstacionamento {
		

	ParkingManager manager;
	
	@Before
	public void carregaManager(){
		
		manager = ParkingManager.getInstance();
		
	}
	
	@After
	public void resetandoVagasDoEstacionamento(){
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
	public void testNaoDeveMaisAceitarVeiculosComEstacionamentoCheio() throws VehicleAlreadyRegistered, ParkingVehicleFullException{
		
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
	
	@Test(expected=VehicleNotRegisteredException.class)
	public void testDeveGerarExcecaoQuandoRegistrarASaidaDeUmVeiculoInexistente() throws VehicleNotRegisteredException{
		
		Vehicle vehicle = new Vehicle("Ford Fusion","200-0050");
		
		manager.registerOut(vehicle);
	}
	
	@Test
	public void testDeveLiberarAVagaDoCarroQueEstavaEstacionado() throws VehicleAlreadyRegistered, ParkingVehicleFullException, VehicleNotRegisteredException{
		/* 
		 * Registrar vários carros ( uns 6 );
		 * Registrar a saída de um veículo intermediário selecionado;
		 * Retornar pela posição e ser igual a null ( indicando que aquela posição o carro foi liberado corretamente );
		*/
		
		List<String> boards = Arrays.asList("200-0001","200-0002","200-0003",
			    							"200-0004","200-0005","200-0006");
		
		for( String board : boards){
			Vehicle vehicle = new Vehicle("Ford Fusion",board);
			manager.registerEntry(vehicle);
		}
		
		Vehicle vehicleSelected = manager.getByPosition(3);
		
		manager.registerOut(vehicleSelected);
		
		assertNull(manager.getByPosition(3));
		
	}
	
	@Test
	public void testDeveRegistrarASaidaDeUmVeiculoIntermediarioEInserirNaPrimeiraPosicaoLivreNaoSendoAPrimeira() throws VehicleAlreadyRegistered, ParkingVehicleFullException, VehicleNotRegisteredException{
		
		List<String> boards = Arrays.asList("200-0001","200-0002","200-0003",
			    							"200-0004","200-0005","200-0006");
		
		for( String board : boards){
			Vehicle vehicle = new Vehicle("Ford Fusion",board);
			manager.registerEntry(vehicle);
		}
		
		
		Vehicle firstOut = manager.getByPosition(3);
		Vehicle secondOut = manager.getByPosition(6);
		
		//Registrando a saída do veículo na posição número 3(indice 2) e 6(indice 5);		
		manager.registerOut(firstOut);
		manager.registerOut(secondOut);
		
		Vehicle new_vehicle = new Vehicle("Ford Fusion","200-EART");
		manager.registerEntry(new_vehicle);
		
		// Verificando usando a busca da posição pela placa
		// int positionInserted = manager.getPositionByBoard(new_vehicle.getBoard());
		
		// Retornando a posição que deveria ter inserido o veículo e verificando que a o carro de tal placa
		// realmente desejava-se estacionar. é a do carro que
		
		Vehicle positionInserted = manager.getByPosition(3);
		
		assertEquals(positionInserted.getBoard(),"200-EART");
	}

	
	
}
