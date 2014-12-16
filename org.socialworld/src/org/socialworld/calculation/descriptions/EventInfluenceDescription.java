/**
 * 
 */
package org.socialworld.calculation.descriptions;

import org.socialworld.calculation.Expression;
import org.socialworld.calculation.FunctionByExpression;

/**
 * The class holds all informations for
 *         describing how an event influences a simulation object.

 * @author Mathias Sikos (tyloesand)   
 */
public class EventInfluenceDescription {

	protected FunctionByExpression f_eventInfluence;
	
	public EventInfluenceDescription(Expression startExpression) {
		f_eventInfluence = new FunctionByExpression(startExpression);
	}
	
	public FunctionByExpression getFunctionEventInfluence() {
		return f_eventInfluence;
	}



}