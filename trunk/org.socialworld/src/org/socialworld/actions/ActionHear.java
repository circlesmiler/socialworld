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
package org.socialworld.actions;

import org.socialworld.attributes.ActionMode;
import org.socialworld.attributes.ActionProperty;
import org.socialworld.attributes.ActionType;
import org.socialworld.attributes.Time;
import org.socialworld.calculation.Vector;
import org.socialworld.objects.SimulationObject;

/**
 * @author Mathias Sikos
 *
 */
public class ActionHear extends AbstractAction {

	private Vector direction;

	public ActionHear(final ActionType type, final ActionMode mode,
			final SimulationObject target, final Vector direction,
			final double intensity, final Time minTime, final Time maxTime,
			final int priority, final long duration) {
		
		setBaseProperties(type,  mode,
			target, 
			intensity,  minTime, maxTime,
			 priority,  duration);
		
		this.setDirection(direction);

	}
	
	public ActionHear(ActionHear original) {
		setBaseProperties(original);
		this.direction = original.direction;
	}

	
	public  Object getConcreteProperty(ActionProperty prop) {
		switch (prop) {
		case direction:
				return getDirection();
		default:
			return null;
		}
	}

	/**
	 * @return the direction
	 */
	public Vector getDirection() {
		return this.direction;
	}

	/**
	 * @param direction
	 *            the direction to set
	 */
	public void setDirection(final Vector direction) {
		this.direction = direction;
	}

	
}
