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
package org.socialworld.calculation.application;

import org.socialworld.objects.SimulationObject;
import org.socialworld.core.Action;
import org.socialworld.core.Event;

public class EventCreator  {

	
	

	/**
	 * The method creates a new event for an action.
	 */
	public static Event createEvent(
			final SimulationObject actor,
			final Action action) {
		
			
			return createEvent(action, actor);
			
	}
	
	/**
	 * The method creates a new event.
	 * Therefore an according mapping description is used to set the event properties.
	 * 
	 */
	private static Event createEvent(final Action action,	final SimulationObject actor) {
		return Action2EventMapping.createEvent(action, actor);
	}
	
	
}
