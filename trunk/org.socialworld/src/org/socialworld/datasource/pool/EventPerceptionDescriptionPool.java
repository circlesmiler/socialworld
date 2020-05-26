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
package org.socialworld.datasource.pool;


import org.socialworld.calculation.Expression;
import org.socialworld.calculation.FunctionByExpression;
import org.socialworld.calculation.descriptions.EventPerceptionDescription;
import org.socialworld.calculation.expressions.CreateKnowledgeElementExpression;
import org.socialworld.core.EventType;

public class EventPerceptionDescriptionPool extends DescriptionPool {

	// temporary for test data
	private FunctionByExpression expressions[];
	public static final int COUNT_FbE_TEST_ENTRIES = 1;		// Anzahl Testeintraege FunctionByExpression

	private static EventPerceptionDescriptionPool instance;
	
	private EventPerceptionDescription descriptions[];
	
	
	private EventPerceptionDescriptionPool () {
		
		sizeDescriptionsArray = EventType.MAX_EVENT_TYPE * GaussPoolPerceptionType.CAPACITY_GPPT_ARRAY;
		descriptions = new EventPerceptionDescription[sizeDescriptionsArray];

		expressions = new FunctionByExpression[COUNT_FbE_TEST_ENTRIES];
		
		initializeWithTestData_FunctionByExpression();
		initialize();
		
	}
	
	public static EventPerceptionDescriptionPool getInstance() {
		if (instance == null) {
			instance = new EventPerceptionDescriptionPool();
		}
		return instance;
	}

	public EventPerceptionDescription getDescription(int eventType,	int perceptionType) {
		int index;

		EventPerceptionDescription description = null;
		
		index = eventType *  GaussPoolPerceptionType.CAPACITY_GPPT_ARRAY + perceptionType ;
		
		if (index >= 0 & sizeDescriptionsArray > index) 	
			description = descriptions[index];
		else
			// create a dummy description with an expression that returns the invalid "nothing" value
			description = new EventPerceptionDescription();
			
		return description;
	}
	
	public void setDescription(int eventType,	int perceptionType, EventPerceptionDescription epd) {
		int index;
			
		index = eventType * GaussPoolPerceptionType.CAPACITY_GPPT_ARRAY + perceptionType;
		
		if (index >= 0 & sizeDescriptionsArray > index) 
			 descriptions[index] = epd;
		
	}


	private void initializeWithTestData_FunctionByExpression() {

		int 		expressionsCount = COUNT_FbE_TEST_ENTRIES;
		String descriptions[] = new String[COUNT_FbE_TEST_ENTRIES];
		Expression startExpression;

		// example for developing
		descriptions[0] = 
		"KSbj:GETVal(eventProps).GETVal(target);" +
		"KSrcT:1," +
		"KSrc:GETVal(myself)," +
		"KFC:0," +
		"KProp:GETVal(eventProps).GETVal(target).GETProp(stateInventory).GETProp(clothes).GETProp(shirt).GETFctVal(getMainColour)," +
		"KProp:GETVal(eventProps).GETVal(target).GETProp(stateBody).GETFctVal(getHead).GETFctVal(getScinColour)," +
		"KProp:GETVal(eventProps).GETVal(target).GETProp(stateBody).GETFctVal(getHead).GETFctVal(getHairColour);" +
		"KSrcT:1," +
		"KSrc:GETVal(myself)," +
		"KFC:1," +
		"KProp:GETVal(eventProps).GETVal(target).GETProp(stateInventory).GETProp(clothes).GETProp(shirt).GETFctVal(getMainMaterial)," +
		"KProp:GETVal(eventProps).GETVal(target).GETProp(stateInventory).GETProp(clothes).GETProp(trousers).GETFctVal(getMainMaterial)," +
		"KProp:GETVal(eventProps).GETVal(target).GETProp(stateInventory).GETProp(clothes).GETProp(shoes).GETFctVal(getMainMaterial)," +
		"KProp:GETVal(eventProps).GETVal(target).GETProp(stateInventory).GETProp(clothes).GETProp(cap).GETFctVal(getMainMaterial)" ;

		
		for (int i = 0; i <  expressionsCount; i++) {
			
			startExpression = new CreateKnowledgeElementExpression(descriptions[i]);
			this.expressions[i] = new FunctionByExpression(startExpression);

		}

		
	}
	
	protected void initialize() {

		EventPerceptionDescription description;
		int indexExps;
		int linesCount = COUNT_FbE_TEST_ENTRIES;
		
		for (int index = 0; index < sizeDescriptionsArray; index++) {
			
			description = new EventPerceptionDescription();
			for (indexExps = 0; indexExps <  linesCount; indexExps++) {
				description.addFunctionCreatePerception(expressions[indexExps]);
			}
			descriptions[index] = description;
	
		}
		
	}


}
