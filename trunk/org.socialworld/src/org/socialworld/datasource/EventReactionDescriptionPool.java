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
package org.socialworld.datasource;

import org.socialworld.calculation.descriptions.EventReactionDescription;
import org.socialworld.core.Event;

public class EventReactionDescriptionPool extends DescriptionPool {

	private static EventReactionDescriptionPool instance;
	
	private static EventReactionDescription descriptions[];
	
	private EventReactionDescriptionPool () {
		
		sizeDescriptionsArray = Event.MAX_EVENT_TYPE * ReactionTypePool.CAPACITY_RTP_ARRAY;
		descriptions = new EventReactionDescription[sizeDescriptionsArray];

		initialize();
	}
	
	public static EventReactionDescriptionPool getInstance() {
		if (instance == null) {
			instance = new EventReactionDescriptionPool();
		}
		return instance;
	}
	
	public EventReactionDescription getDescription(int eventType,	int reactionType) {
		int index;

		EventReactionDescription description = null;
		
		index = eventType *  ReactionTypePool.CAPACITY_RTP_ARRAY + reactionType ;
		
		if (sizeDescriptionsArray > index) 			description = descriptions[index];
		return description;
	}

	protected void initialize() {
		initializeFromFile();
	}
	
	private void initializeFromFile() {
		
	}

}