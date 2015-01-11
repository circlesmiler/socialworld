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
package org.socialworld.objects;


import org.apache.log4j.Logger;
import org.socialworld.actions.AbstractAction;
import org.socialworld.attributes.Position;
import org.socialworld.core.ActionHandler;
import org.socialworld.core.Event;
import org.socialworld.core.Simulation;
import org.socialworld.propertyChange.ListenedBase;

/**
 * Every simulation object (actor in the simulation) is inherited by the abstract class SimulationObject.
 * 
 * @author Mathias Sikos (tyloesand)
 * 
 */
public abstract class SimulationObject extends ListenedBase {
	protected static final Logger logger = Logger.getLogger(SimulationObject.class);

	private	WriteAccessToSimulationObject guard;
	private		int 			objectID;
	protected   Simulation  	simulation;
	

	protected 	ActionHandler 	actionHandler;
	
	protected	int				influenceTypeByEventType[];
	protected	int				reactionTypeByEventType[];
	protected   int				state2ActionType;
	
	private StateSimulationObject state;
	
	/**
	 * The constructor creates a simulation object.
	 * 
	 */
	public SimulationObject(StateSimulationObject state) {
		this.guard = null;
		
		this.simulation = Simulation.getInstance();
		
		this.state = state;
		state.setObject(this);
		
		this.actionHandler = new ActionHandler(this);

	}

	
	final StateSimulationObject getState(WriteAccessToSimulationObject guard) {
		if (checkGuard(guard))
			return this.state;
		else
			return null;
		
	}
	
	final void setState(StateSimulationObject state, WriteAccessToSimulationObject guard) {
		if (checkGuard(guard)) this.state = state;
	}
	
	/**
	 * The method sets write access to a guard.
	 * This guard can manipulate the object's state by calling methods.
	 * Only the guard has access to object manipulation methods.
	 * There is only one guard for a simulation object.
	 * So a guard can only be set if no guard has been set yet.
	 * 
	 * @param guard
	 */
	final void setWriteAccess(WriteAccessToSimulationObject guard) {
		if (this.guard == null)  this.guard = guard;
	}
	
	
	final boolean checkGuard(WriteAccessToSimulationObject guard) {
		return (this.guard == guard);
	}
	
	void setObjectID(int objectID, WriteAccessToSimulationObject guard) {
		if (this.guard == guard ) this.objectID = objectID;	
	}

	void setPosition (Position pos, WriteAccessToSimulationObject guard) {
		if (this.guard == guard) 
			this.state.setPosition(pos);
	}

	public void setAction(AbstractAction newAction, WriteAccessToSimulationObject guard) {

		if (this.guard == guard) {
			if (Simulation.WITH_LOGGING == 1 ) logger.debug("Mensch " + objectID + " tr�gt Aktion " + newAction.toString() + " in Liste ein");
			actionHandler.insertAction(newAction);
		}
	}

	void setInfluenceTypes (int types[], WriteAccessToSimulationObject guard) {
		if (this.guard == guard) this.influenceTypeByEventType = types;
	}

	void setReactionTypes (int types[], WriteAccessToSimulationObject guard) {
		if (this.guard == guard) this.reactionTypeByEventType = types;
	}

	void setState2ActionType (int type, WriteAccessToSimulationObject guard) {
		if (this.guard == guard) this.state2ActionType = type;
	}
	
	public void addAction(AbstractAction newAction) {
		actionHandler.insertAction(newAction);
	}
	
	/**
	 * The method lets a simulation object do an action.
	 * 
	 * @param action
	 */
	public void doAction(AbstractAction action) {
		action.setActor(guard);
		action.perform();
		action.removeWriteAccess();
	}

	
	
	/**
	 * The method determines the influence of an event. It calculates how the
	 * event changes the attributes of the simulation object.
	 * 
	 * @param simualationEvent -
	 *            the event influencing the simulation object
	 */
	public abstract void changeByEvent(final Event simualationEvent);

	/**
	 * The method determines the reaction to an event.
	 * It creates an action object and inserts it into the action handler list.
	 * 
	 * @param simualationEvent -
	 *            the event influencing the simulation object
	 */
	public abstract void reactToEvent(final Event simualationEvent);

	/**
	 * The method returns the object's position.
	 * 
	 * @return position
	 */
	public Position getPosition() {
		return new Position(this.state.getPosition());
	}

	
	

	/**
	 * The method returns the reference to object's action handler.
	 * 
	 * @return actionHandler
	 */
	public ActionHandler getActionHandler() {
		return null;
	}
	
	/**
	 * The method loads the array of influence types for all event types.
	 */

	/**
	 * The method loads the array of reaction types for all event types.
	 */
	
	public int getReactionType(int eventType) {
	 return reactionTypeByEventType[eventType];
	} 

	public int getInfluenceType(int eventType) {
		 return influenceTypeByEventType[eventType];
		} 

	public int getState2ActionType() {
		 return state2ActionType;
		} 

	public int getObjectID() {
		return objectID;
	}
	
	public String toString() {
		return "" + objectID;
	}
}
