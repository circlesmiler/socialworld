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

import java.util.ArrayList;
import java.util.List;

import org.socialworld.attributes.PropertyName;
import org.socialworld.calculation.FunctionByExpression;
import org.socialworld.calculation.FunctionByMatrix;
import org.socialworld.calculation.FunctionByMatrix_Matrix;
import org.socialworld.calculation.SimulationCluster;
import org.socialworld.calculation.Type;
import org.socialworld.calculation.Value;
import org.socialworld.calculation.ValueTransferCode;
import org.socialworld.calculation.descriptions.EventInfluenceAssignment;
import org.socialworld.calculation.descriptions.EventInfluenceDescription;
import org.socialworld.collections.ValueArrayList;
import org.socialworld.core.Event;
import org.socialworld.core.SocialWorldThread;
import org.socialworld.objects.StateAnimal;
import org.socialworld.objects.access.HiddenAnimal;


public  class AttributeCalculator extends SocialWorldThread {

	public static final int ATTRIBUTE_CALCULATOR_RETURNS_EMPTY_LISTS = 2;
	public static final int ATTRIBUTE_CALCULATOR_RETURNS_NO_CHANGES = 1;
	public static final int ATTRIBUTE_CALCULATOR_RETURNS_INVALID_RESULT = 3;

	private static AttributeCalculator instance;

	private List<Event> events;
	private List<StateAnimal> states4Influence;
	private List<HiddenAnimal> hiddenAnimals4Influence;

	private List<StateAnimal> states4Change;
	private List<HiddenAnimal> hiddenAnimals4Change;

	private List<StateAnimal> states4Refresh;
	private List<HiddenAnimal> hiddenAnimals4Refresh;
	
	/**
	 * private Constructor. 
	 */
	private AttributeCalculator() {

		this.events = new ArrayList<Event>();
		this.states4Influence = new ArrayList<StateAnimal>();
		this.hiddenAnimals4Influence = new ArrayList<HiddenAnimal>();

		this.states4Change = new ArrayList<StateAnimal>();
		this.hiddenAnimals4Change = new ArrayList<HiddenAnimal>();

		this.states4Refresh = new ArrayList<StateAnimal>();
		this.hiddenAnimals4Refresh = new ArrayList<HiddenAnimal>();
		
	}

	public static AttributeCalculator getInstance() {
		if (instance == null) {
			instance = new AttributeCalculator();
		}
		return instance;
	}
	
	public void run() {

		while (isRunning()) {
			
			calculateAttributesChangedByEvent();
			calculateAttributesChangedByComplexMatrix();
			calculateAttributesChangedBySimpleMatrix();
			
			try {
				sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	final void calculateAttributesChangedByEvent(Event event, StateAnimal stateAnimal, HiddenAnimal hiddenWriteAccess) {
		if (event != null && stateAnimal != null && hiddenWriteAccess != null) {
			this.events.add(event);
			this.states4Influence.add(stateAnimal);
			this.hiddenAnimals4Influence.add( hiddenWriteAccess);
		}
	}

	final void calculateAttributesChangedByComplexMatrix(StateAnimal stateAnimal, HiddenAnimal hiddenWriteAccess) {
		if (stateAnimal != null && hiddenWriteAccess != null) {
			this.states4Change.add(stateAnimal);
			this.hiddenAnimals4Change.add( hiddenWriteAccess);
		}
	}
	
	final void calculateAttributesChangedBySimpleMatrix(StateAnimal stateAnimal, HiddenAnimal hiddenWriteAccess) {
		this.states4Refresh.add(stateAnimal);
		this.hiddenAnimals4Refresh.add( hiddenWriteAccess);
	}
	
	private final int calculateAttributesChangedByEvent() {
		
		if ((this.events.size() == 0) || 
			(this.states4Influence.size() == 0) ||
			(this.hiddenAnimals4Influence.size() == 0)) 
		{
			return ATTRIBUTE_CALCULATOR_RETURNS_EMPTY_LISTS;
		}
		
		Event event = this.events.remove(0);
		StateAnimal stateAnimal  = this.states4Influence.remove(0);
		HiddenAnimal hiddenWriteAccess = this.hiddenAnimals4Influence.remove(0);
		
		Value resultAttributeArray;

		resultAttributeArray = getAttributesChangedByEvent(event, stateAnimal);
		
		if (resultAttributeArray.isValid()) {
			if (resultAttributeArray.getTransferCode() == ValueTransferCode.noChanges) {
				return ATTRIBUTE_CALCULATOR_RETURNS_NO_CHANGES;
			}
			return hiddenWriteAccess.setAttributes(resultAttributeArray);
		}
		else
			return ATTRIBUTE_CALCULATOR_RETURNS_INVALID_RESULT;
		
	}
	
	
	/**
	 * The method sets the event influence description to an instance variable
	 * and starts the calculation.
	 * 
	 * @param eventInfluence
	 *            the informations how an event effects to the attributes.
	 */
	private static Value getAttributesChangedByEvent(Event event, StateAnimal stateAnimal) {
		EventInfluenceDescription eventInfluenceDescription = null;
		int eventType;
		int eventInfluenceType;
		Value oldAttributes;
		Value newAttributes = new Value();
		
		FunctionByExpression f_EventInfluence = null;
		ValueArrayList arguments;
	
		eventType = event.getEventTypeAsInt();
		eventInfluenceType = stateAnimal.getInfluenceType(eventType);
		
		eventInfluenceDescription = 
			EventInfluenceAssignment.getInstance().getEventInfluenceDescription(
				eventType, eventInfluenceType	);

		int count = eventInfluenceDescription.countFunctions();
		
		oldAttributes =  stateAnimal.getProperty(SimulationCluster.attributeArray, PropertyName.simobj_attributeArray);
		arguments = new ValueArrayList();
		arguments.add( oldAttributes );
		
		for (int index = 0; index < count; index++){
			
			f_EventInfluence = eventInfluenceDescription.getFunctionEventInfluence(index);
			newAttributes = f_EventInfluence.calculate(arguments);
			arguments.set(0,  newAttributes);
			
		}
	  
		if (newAttributes.isValid()){
			if (oldAttributes.equals(newAttributes)) {
				newAttributes.setTransferCode(ValueTransferCode.noChanges);
			}
			else {
			}
			return newAttributes;
		}
		else
			return oldAttributes;
	}

	private final int calculateAttributesChangedByComplexMatrix() {
		
		if (this.hiddenAnimals4Change.size() == 0) return ATTRIBUTE_CALCULATOR_RETURNS_EMPTY_LISTS;
		
		StateAnimal stateAnimal  = this.states4Change.remove(0);
		HiddenAnimal hiddenWriteAccess = this.hiddenAnimals4Change.remove(0);
		
		Value resultAttributeArray;

		resultAttributeArray = getAttributesChangedByComplexMatrix(stateAnimal);
		
		if (resultAttributeArray.isValid()) {
			if (resultAttributeArray.getTransferCode() == ValueTransferCode.noChanges) {
				return ATTRIBUTE_CALCULATOR_RETURNS_NO_CHANGES;
			}
			return hiddenWriteAccess.setAttributes(resultAttributeArray);
		}
		else
			return ATTRIBUTE_CALCULATOR_RETURNS_INVALID_RESULT;

	}

	private static Value getAttributesChangedByComplexMatrix(StateAnimal stateAnimal) {
		FunctionByMatrix f_AttributesByMatrix;
		ValueArrayList arguments;
		Value oldAttributes;
		Value newAttributes = new Value();
	
		oldAttributes =  stateAnimal.getProperty(SimulationCluster.attributeArray, PropertyName.simobj_attributeArray);

		arguments = new ValueArrayList();
		arguments.add( oldAttributes );
		arguments.add( new Value(Type.integer, FunctionByMatrix_Matrix.CALCULATION_MODE_MATRIX_X_VECTOR_COMPLEX ) );
		
		f_AttributesByMatrix = stateAnimal.getMatrix();
		newAttributes = f_AttributesByMatrix.calculate(arguments);;
		
		if (newAttributes.isValid()){
			if (oldAttributes.equals(newAttributes)) {
				newAttributes.setTransferCode(ValueTransferCode.noChanges);
			}
			else {
			}
			return newAttributes;
		}
		else
			return oldAttributes;

	}
	
	private final int calculateAttributesChangedBySimpleMatrix() {
		
		if (this.hiddenAnimals4Refresh.size() == 0) return ATTRIBUTE_CALCULATOR_RETURNS_EMPTY_LISTS;
		
		StateAnimal stateAnimal  = this.states4Refresh.remove(0);
		HiddenAnimal hiddenWriteAccess = this.hiddenAnimals4Refresh.remove(0);
		
		Value resultAttributeArray;
		
		resultAttributeArray = getAttributesChangedBySimpleMatrix(stateAnimal);
		
		if (resultAttributeArray.isValid()) {
			if (resultAttributeArray.getTransferCode() == ValueTransferCode.noChanges) {
				return ATTRIBUTE_CALCULATOR_RETURNS_NO_CHANGES;
			}
			return hiddenWriteAccess.setAttributes(resultAttributeArray);
		}
		else
			return ATTRIBUTE_CALCULATOR_RETURNS_INVALID_RESULT;

	}
	
	private static Value getAttributesChangedBySimpleMatrix(StateAnimal stateAnimal) {
		FunctionByMatrix f_AttributesByMatrix;
		ValueArrayList arguments;
		Value oldAttributes;
		Value newAttributes = new Value();
	
		oldAttributes =  stateAnimal.getProperty(SimulationCluster.attributeArray, PropertyName.simobj_attributeArray);

		arguments = new ValueArrayList();
		arguments.add( oldAttributes );
		arguments.add( new Value(Type.integer, FunctionByMatrix_Matrix.CALCULATION_MODE_MATRIX_X_VECTOR_SIMPLE) );
		
		f_AttributesByMatrix = stateAnimal.getMatrix();
		newAttributes = f_AttributesByMatrix.calculate(arguments);;
		
		if (newAttributes.isValid()){
			if (oldAttributes.equals(newAttributes)) {
				newAttributes.setTransferCode(ValueTransferCode.noChanges);
			}
			else {
			}
			return newAttributes;
		}
		else
			return oldAttributes;
	}

	/**
	 * The method checks whether the calculation loop continues. The calculation
	 * loop stops if for every attribute the value changes by less than 1.
	 * 
	 * @return true if the calculation loop continues and false if the
	 *         calculation loop stops
	 */
/*	private boolean checkEpsilon() {
		int row = 0;

		while ((attributesNew[row] + 1) > (float) this.attributes.get(row)
				|| (attributesNew[row] - 1) < (float) this.attributes.get(row)) {
			row++;
			if (row == this.numberOfAttributes)
				return false;
		}
		return true;
	}
*/

}

