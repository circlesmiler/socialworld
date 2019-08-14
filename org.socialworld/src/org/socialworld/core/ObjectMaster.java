/*
* Social World
* Copyright (C) 2014  Mathias Sikos
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; either version 2
* of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.  
*
* or see http://www.gnu.org/licenses/gpl-2.0.html
*
*/
package org.socialworld.core;

import java.util.ListIterator;

import org.socialworld.objects.*;
import org.socialworld.propertyChange.ListenedList;
import org.socialworld.datasource.createObjects.CreateAnimal;
import org.socialworld.datasource.createObjects.CreateGod;
import org.socialworld.datasource.createObjects.CreateHuman;
import org.socialworld.datasource.createObjects.CreateItem;
import org.socialworld.datasource.createObjects.CreateMagic;
import org.socialworld.datasource.createObjects.CreateSimulationObjects;
import org.socialworld.datasource.loadObjects.LoadAnimal;
import org.socialworld.datasource.loadObjects.LoadGod;
import org.socialworld.datasource.loadObjects.LoadHuman;
import org.socialworld.datasource.loadObjects.LoadItem;
import org.socialworld.datasource.loadObjects.LoadMagic;
import org.socialworld.datasource.loadObjects.LoadSimulationObjects;
import org.socialworld.datasource.tablesSimulation.TableObject;

import org.socialworld.collections.SimulationObjectArray;

/**
 * @author Mathias Sikos (tyloesand)
 *
 */
public class ObjectMaster {

	private static final int SIMULATION_OBJECTS_START_CAPACITY = 1000;
	
	private static ObjectMaster instance;
	
	private LoadSimulationObjects[] loaders;
	private CreateSimulationObjects[] creators;
	
	private final ListenedList<God> gods;
	private final ListenedList<Human> humans;
	private final ListenedList<Item> items;
	private final ListenedList<Magic> magics;
	private final ListenedList<Animal> animals;
	
	private final SimulationObjectArray simulationObjects;

	private int maxObjectID = 0;
	
	private  ListIterator<God> godsIterator;
	private  ListIterator<Human> humansIterator;
	private  ListIterator<Item> itemsIterator;
	private  ListIterator<Magic> magicsIterator;
	private  ListIterator<Animal> animalsIterator;
	
	
	private ObjectMaster() {

		this.simulationObjects = new SimulationObjectArray(SIMULATION_OBJECTS_START_CAPACITY);
		
		this.gods = new ListenedList<God>();
		this.humans = new ListenedList<Human>();
		this.animals = new ListenedList<Animal>();
		this.magics = new ListenedList<Magic>();
		this.items = new ListenedList<Item>();
		resetIterators();
		
		loaders = new LoadSimulationObjects[8];
		loaders[1] = LoadAnimal.getExlusiveInstance(simulationObjects);
		loaders[2] = LoadHuman.getExlusiveInstance(simulationObjects);
		loaders[3] = LoadGod.getExlusiveInstance(simulationObjects);
		loaders[4] = LoadItem.getExlusiveInstance(simulationObjects);
		loaders[5] = LoadMagic.getExlusiveInstance(simulationObjects);

		creators = new CreateSimulationObjects[8];
		creators[1] = CreateAnimal.getExlusiveInstance();
		creators[2] = CreateHuman.getExlusiveInstance();
		creators[3] = CreateGod.getExlusiveInstance();
		creators[4] = CreateItem.getExlusiveInstance();
		creators[5] = CreateMagic.getExlusiveInstance();
		
	}	
	
	public static ObjectMaster getInstance() {
		if (instance == null) {
			instance = new ObjectMaster();
		}
		return instance;
	}
	
	public SimulationObject getSimulationObject(int objectID) {
		return this.simulationObjects.get(objectID);
	}
	
	public void loadSimulationObjects() {
		int allIDs[];
		int length;
		int index;
		
		int objectID;
		int type;
		
		TableObject tableObjects;
		tableObjects = new TableObject();
		
		tableObjects.select(tableObjects.SELECT_COLUMNS_ID_TYPE, "", "ORDER BY id");
		
		allIDs = tableObjects.getAllPK1();
		length = allIDs.length;
		
		for (index = 0; index < length; index++) {
			objectID = allIDs[index];
			type = tableObjects.getType(index);
			
			this.loaders[type].createObject(objectID);
		}
		
		for (index = 0; index < length; index++) {
			objectID = allIDs[index];
			type = tableObjects.getType(index);

			this.loaders[type].loadObject(objectID);
			addObjectToList(SimulationObject_Type.getSimulationObjectType(type), simulationObjects.get(objectID));
		}
		
	}
	
	
	public SimulationObject createSimulationObject(
			SimulationObject_Type simulationObjectType) {
		SimulationObject object;
		int objectID;
		
		maxObjectID = maxObjectID + 1;
		objectID =  maxObjectID;
		
		object = creators[simulationObjectType.getIndex()].getObject(objectID);
		
		if (object != null) {
			this.simulationObjects.set(objectID, object);
			addObjectToList(simulationObjectType, object);
		}
		
		return object;
	}
	
	public int refreshNextObjectsState(SimulationObject_Type simObjType) {
		
		SimulationObject theNextOne;
		
		theNextOne = next(simObjType);
		
		if (theNextOne != null) {
			theNextOne.refreshState();
			return simObjType.getIndex();
		}
		else {
			resetIterator(simObjType);
			return  simObjType.next();
		}
		

	}
	
	private void addObjectToList(SimulationObject_Type simulationObjectType, SimulationObject object) {
		// TODO (tyloesand) weitere Objekttypen hinzufügen
		switch (simulationObjectType) {
		case animal:
			this.animals.add((Animal)object);
			animalsIterator = animals.listIterator(animalsIterator.nextIndex());
			break;
		case human:
			this.humans.add((Human)object);
			humansIterator = humans.listIterator(humansIterator.nextIndex());
			break;
		case god:
			this.gods.add((God)object);
			godsIterator = gods.listIterator(godsIterator.nextIndex());
			break;
		case item:
			this.items.add((Item)object);
			itemsIterator = items.listIterator(itemsIterator.nextIndex());
			break;
		case magic:
			this.magics.add((Magic)object);
			magicsIterator = magics.listIterator(magicsIterator.nextIndex());
			break;
		default:
			object = null;
		}		
	}
	
	private boolean hasNext(SimulationObject_Type simulationObjectType) {

		switch (simulationObjectType) {
		case animal:
			return this.animalsIterator.hasNext();
		case human:
			return this.humansIterator.hasNext();
		case god:
			return this.godsIterator.hasNext();
		case item:
			return this.itemsIterator.hasNext();
		case magic:
			return this.magicsIterator.hasNext();
		default:
			return false;
		}		
		
	}
	
	private SimulationObject next(SimulationObject_Type simulationObjectType) {
		switch (simulationObjectType) {
		case animal:
			if (this.animalsIterator.hasNext())
				return this.animalsIterator.next();
			else
				break;
		case human:
			if (this.humansIterator.hasNext())
				return this.humansIterator.next();
			else
				break;
		case god:
			if (this.godsIterator.hasNext())
				return this.godsIterator.next();
			else
				break;
		case item:
			if (this.itemsIterator.hasNext())
				return this.itemsIterator.next();
			else
				break;
		case magic:
			if (this.magicsIterator.hasNext())
				return this.magicsIterator.next();
			else
				break;
		default:
			return null;
		}		
		
		return null;
		
	}
	
	/**
	 * The method returns the list of gods
	 * 
	 * @return
	 */
	public ListenedList<God> getGods() {
		return this.gods;
	}

	/**
	 * The method returns the list of humans
	 * 
	 * @return
	 */
	public ListenedList<Human> getHumans() {
		return this.humans;
	}

	/**
	 * The method returns the list of items
	 * 
	 * @return
	 */
	public ListenedList<Item> getItems() {
		return this.items;
	}


	/**
	 * The method returns a list of magics
	 * 
	 * @return
	 */
	public ListenedList<Magic> getMagics() {
		return this.magics;
	}	
	
	private void resetIterator(SimulationObject_Type simObjType) {
	
		switch (simObjType) {
		case animal:
			 this.animalsIterator = this.animals.listIterator(); break;
		case human:
			 this.humansIterator = this.humans.listIterator(); break;
		case god:
			 this.godsIterator = this.gods.listIterator(); break;
		case item:
			 this.itemsIterator = this.items.listIterator(); break;
		case magic:
			 this.magicsIterator = this.magics.listIterator(); break;
		default:
		}		

	}
	
	private void resetIterators() {
		this.godsIterator = this.gods.listIterator();
		this.humansIterator = this.humans.listIterator();
		this.animalsIterator = this.animals.listIterator();
		this.magicsIterator = this.magics.listIterator();
//		this.positionsIterator = this.positions.listIterator();
		this.itemsIterator = this.items.listIterator();
//		this.inventorysIterator = this.inventories.listIterator();

	}
	
}
