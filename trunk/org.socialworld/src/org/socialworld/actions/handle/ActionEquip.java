/*
* Social World
* Copyright (C) 2019  Mathias Sikos
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
package org.socialworld.actions.handle;

import org.socialworld.actions.AbstractAction;
import org.socialworld.actions.ActionMode;
import org.socialworld.attributes.ActualTime;
import org.socialworld.attributes.Position;
import org.socialworld.attributes.Time;
import org.socialworld.calculation.Type;
import org.socialworld.calculation.Value;
import org.socialworld.collections.ValueArrayList;
import org.socialworld.core.EventByAction;
import org.socialworld.core.EventType;
import org.socialworld.core.IEventParam;
import org.socialworld.objects.SimulationObject;

public class ActionEquip extends AbstractAction {

	private Equip equip;
	
    private SimulationObject item;
    
    private InventoryPlace inventoryPlace;;

	@Override
	protected void setFurtherProperties(ValueArrayList actionProperties) {

		Value value;
		
		SimulationObject item;
		InventoryPlace inventoryPlace;

		value =  actionProperties.getValue(furtherPropertyNames[0]);
		if (value.isValid()) {
			item =  (SimulationObject) value.getValue() ;
			this.setItem(item);
		}

		value =  actionProperties.getValue(furtherPropertyNames[1]);
		if (value.isValid()) {
			inventoryPlace = InventoryPlace.getName((int) value.getValueCopy());
			this.setInventoryPlace(inventoryPlace);
		}

	}

	@Override
	protected void setFurtherProperties(AbstractAction original) {
		this.setItem(((ActionEquip) original).getItem());
		this.setInventoryPlace(((ActionEquip) original).getInventoryPlace());

	}

	@Override
	public void perform() {

      	this.equip = new Equip( this);
      	
		Position position = actor.getPosition();
		Time actualTime = ActualTime.asTime();
		
      	// event with influence to other objects
      	EventByAction event;
		event = new EventByAction( getEventType(mode, false),    actor /* as causer*/, actualTime ,
				position,  equip /* as performer */);
		addEvent(event);

      	// event with influence to causer itself 
     	EventByAction eventSelf;
     	eventSelf = new EventByAction( getEventType(mode, true),    actor /* as causer*/,  actualTime,
     			position,  equip /* as performer */);
		addEvent(eventSelf);

		
	}

	private EventType getEventType(ActionMode mode, boolean eventToCauserItself) {
		
		EventType eventType;
   			
		if (eventToCauserItself) {
			
			switch (mode) {
	
				case takeItem:
					eventType = EventType.selfInventoryTake;
					break;
				case collectItem:
					eventType = EventType.selfInventoryCollect;
					break;
				case dropItem:
					eventType = EventType.selfInventoryDrop;
					break;
				case switchItemToOtherHand:
					eventType = EventType.selfInventorySwitch;
					break;
				case putToInventory:
					eventType = EventType.selfInventoryPut;
					break;
				default:
					eventType = EventType.nothing;
					
			}
			
		}
		else {
			
			switch (mode) {
			
				case takeItem:
					eventType = EventType.inventoryTake;
					break;
				case collectItem:
					eventType = EventType.inventoryCollect;
					break;
				case dropItem:
					eventType = EventType.inventoryDrop;
					break;
				case switchItemToOtherHand:
					eventType = EventType.inventorySwitch;
					break;
				case putToInventory:
					eventType = EventType.inventoryPut;
					break;
				default:
					eventType = EventType.nothing;
				
			}
			
		}
		
	  	return eventType;
	  	
	}
	
	public void setInventoryPlace(InventoryPlace inventoryPlace) {
		this.inventoryPlace = inventoryPlace;
	}

	private InventoryPlace getInventoryPlace() {
		return this.inventoryPlace;
	}

	public Value getInventoryPlaceAsValue(String valueName) {
		return new Value(Type.integer, valueName, this.inventoryPlace.getIndex());
	}
	
	public void setItem(SimulationObject item) {
		this.item = item;
	}

	private SimulationObject getItem() {
		return this.item;
	}

	public Value getItemAsValue(String valueName) {
		return new Value(Type.simulationObject, valueName, this.item);
	}
	
///////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////    PROPERTY LIST  ///////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

	public void requestPropertyList(IEventParam paramObject) {
	
		super.requestPropertyList(paramObject);
		
		ValueArrayList propertiesAsValueList = new ValueArrayList();
		
		paramObject.answerPropertiesRequest(propertiesAsValueList);
		
	}
	

}