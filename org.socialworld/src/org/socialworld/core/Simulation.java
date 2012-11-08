/**
 * 
 */
package org.socialworld.core;

import org.apache.log4j.Logger;
import org.socialworld.objects.*;



/**
 * The class Simulation holds all simulation objects and runs the simulation
 * 
 * @author Mathias Sikos (tyloesand)
 * 
 */
public class Simulation implements IHumanWrite{
	// TODO (tyloesand) hier ist noch viel zu tun:
	// - ueberdenken, welche Listen
	// - ueberdenken Start EventMaster
	
	private static final Logger logger = Logger.getLogger(Simulation.class);

	private static Simulation instance;
	
	private final EventMaster eventMaster;
	private final ObjectMaster objectMaster;

	private Simulation() {
		
		logger.debug("create simulation object");
		this.eventMaster = new EventMaster();
		this.objectMaster = new ObjectMaster();

	}
	
	/**
	 * The method gets back the only instance of the Simulation.
	 * 
	 * @return singleton object of Simulation
	 */
	public static Simulation getInstance() {
		if (instance == null) {
			instance = new Simulation();
		}
		return instance;
		
	}
	
	
	public void startSimulation() {
		this.eventMaster.start();
	}
	
	public void stopSimulation() {
		this.eventMaster.stopEventMaster();
	}

	public EventMaster getEventMaster() {
		return this.eventMaster;
	}

	// for test visualizes there is a public access to the object master
	public ObjectMaster getObjectMaster() {
		return this.objectMaster;
	}
	
	public SimulationObject createSimulationObject(
			SimulationObjectType simulationObjectType, long objectID) {
		SimulationObject object;
		object = this.objectMaster.createSimulationObject(simulationObjectType, objectID);
		return object;
	}

	public boolean hasNext(SimulationObjectType objectType) {
		return this.objectMaster.hasNext(objectType);
	}
	
	public SimulationObject next(SimulationObjectType objectType) {
		return this.objectMaster.next(objectType);
	}
	
}