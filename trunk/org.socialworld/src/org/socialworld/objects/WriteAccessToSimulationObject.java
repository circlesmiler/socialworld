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
import org.socialworld.actions.AbstractAction;
import org.socialworld.attributes.Position;

public class WriteAccessToSimulationObject {
	private SimulationObject object;
	
	public WriteAccessToSimulationObject(SimulationObject object) {
		this.object = object;
		object.setWriteAccess(this);
	}
	
	public SimulationObject getObject() {
		return this.object;
	}
	
	public final boolean checkGrantRightFor(Object request) {
		switch (request.getClass().getName()) {
		case "org.socialworld.actions.move.ActionMove":
			return true;
		case "org.socialworld.actions.move.ActionAttack":
			return true;
		default:
			return false;
		}
	}
	
	public void setObjectID(int objectID, Object caller) {
		if (caller instanceof ISimulationObjectWrite) object.setObjectID(objectID, this);
	}

	public void setPosition(Position pos, Object caller) {
		if (caller instanceof ISimulationObjectWrite) object.setPosition(pos, this);
	}
	
	public void setAction(AbstractAction action, Object caller) {
		if (caller instanceof ISimulationObjectWrite) object.setAction(action, this);
	}

	public void setInfluenceTypes(int types[], Object caller) {
		if (caller instanceof ISimulationObjectWrite) object.setInfluenceTypes(types, this);
	}

	public void setReactionTypes(int types[], Object caller) {
		if (caller instanceof ISimulationObjectWrite) object.setReactionTypes(types, this);
	}

	public void setState2ActionType(int type, Object caller) {
		if (caller instanceof ISimulationObjectWrite) object.setState2ActionType(type, this);
	}
	
	
}
