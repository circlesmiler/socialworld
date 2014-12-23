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
import org.socialworld.core.Event;

public class ReactionTypePool {
	public static final int CAPACITY_RTP_ARRAY = 100;

	private static ReactionTypePool instance;

	private static int[] reactionTypesForPositiveIndex;
	private static int[] reactionTypesForNegativeIndex;

	private ReactionTypePool() {
		reactionTypesForPositiveIndex = new int[(CAPACITY_RTP_ARRAY + 1) * Event.MAX_EVENT_TYPE];
		reactionTypesForNegativeIndex = new int[(CAPACITY_RTP_ARRAY + 1) * Event.MAX_EVENT_TYPE];
	}

	public static ReactionTypePool getInstance() {
		if (instance == null) {
			instance = new ReactionTypePool();
		}
		return instance;
	}

	public int[] getReactionTypes(int indexByGauss) {
		int types[];
		int index;
		
		types = new int[Event.MAX_EVENT_TYPE];
	
		if (indexByGauss >= 0)
			for (int eventType = 0; eventType < Event.MAX_EVENT_TYPE; eventType++){
				index = indexByGauss * Event.MAX_EVENT_TYPE  + eventType;
				types[eventType] = reactionTypesForPositiveIndex[index];
			}
		else {
			indexByGauss = indexByGauss * -1;
			for (int eventType = 0; eventType < Event.MAX_EVENT_TYPE; eventType++){
				index = indexByGauss * Event.MAX_EVENT_TYPE  + eventType;
				types[eventType] = reactionTypesForNegativeIndex[index];
			}
		}	
		
		return types;
	}
	

}
