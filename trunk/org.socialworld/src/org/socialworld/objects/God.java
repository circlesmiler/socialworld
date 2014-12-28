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

import org.socialworld.core.Action;
import org.socialworld.core.Event;
import org.socialworld.attributes.ActionType;

/**
 * The God is an special simulation object that is responsible for global
 * events. The god is responsible for weather for example.
 * 
 * @author Mathias Sikos (tyloesand)
 * 
 */
public class God extends SimulationObject {

	public God() {
		super();
	}

	@Override
	public void changeByEvent(final Event event) {
		// TODO Implement method
	}

	public void reactToEvent(final Event event) {
		
	}

	@Override
	protected void doAction(final ActionType type, final Action action) {
	}
}