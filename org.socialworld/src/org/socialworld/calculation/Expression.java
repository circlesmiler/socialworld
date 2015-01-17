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
package org.socialworld.calculation;

import org.socialworld.attributes.AttributeArray;

/**
 * The class is an implementation of an
 *         expression. The expression is part of a term that consists of
 *         mathematical comparisons and (mathematical) operations which are
 *         executed dependent to the evaluation result of the comparison. An
 *         expression is a comparison to a constant OR addition of a constant 
 *          OR a multiplication OR a replacement 
 *         If the expression is a comparison there are given two
 *         further expressions for the boolean evaluation result of the
 *         comparison. So a expression evaluates an expression recursively.

 * @author Mathias Sikos (tyloesand)
 */
public class Expression {
	int ID;
	
	Expression_Function operation;
	Expression_ConditionOperator operator;
	FunctionBase function;
	
	Value value;
	
	Expression expression1;
	Expression expression2;
	Expression expression3;
	
	int ID_Exp1;
	int ID_Exp2;
	int ID_Exp3;
	
	private Calculation calculation;
	

	public Expression() {
		calculation = Calculation.getInstance();
		operation = Expression_Function.nothing;
	}
	
	
	public void setID(int ID) {		this.ID = ID;	}

	public void setID(String ID) {		this.ID = (int) Float.parseFloat(ID);	}

	public int getID() {return ID; };

	public void set_ID_Exp2(int ID) {		this.ID_Exp2 = ID;	}

	public void set_ID_Exp2(String ID) {		this.ID_Exp2 = (int) Float.parseFloat(ID);	}
	
	public int get_ID_Exp2() {return ID_Exp2; };
	
	public void set_ID_Exp3(int ID) {		this.ID_Exp3 = ID;	}

	public void set_ID_Exp3(String ID) {		this.ID_Exp3 = (int) Float.parseFloat(ID);	}

	public int get_ID_Exp3() {return ID_Exp3; };

	public void setOperation(Expression_Function operation) {
		this.operation = operation;
	}

	public void setOperator(Expression_ConditionOperator operator) {
		this.operator = operator;
	}

	
	public void setExpression1(Expression expression) {
		this.expression1 = expression;
	}
	
	public void setExpression2(Expression expression) {
		this.expression2 = expression;
	}

	public void setExpression3(Expression expression) {
		this.expression3 = expression;
	}

	
	public void setTrueExpression(Expression expressionForTrue) {
		setExpression2(expressionForTrue);
	}

	public void setFalseExpression(Expression expressionForFalse) {
		setExpression3(expressionForFalse);
	}

	public void setValue(Value value) {
		this.value = value;
	}

	protected Value getValue() {
		return value;
	}
	
		


	/**
	 */
	Value evaluateExpression(Value[] arguments) {
		Value tmp;
		
		switch (this.operation) {
		case nothing:
			//return invalid dummy-Value
			return Calculation.getNothing();
		case value:
			return calculation.copy(value);
			
		case attributeValue:
			AttributeArray attributeArray;
			attributeArray = (AttributeArray) get( arguments, Type.attributeArray, 1);
			return calculation.createValue(
				Type.integer,
				attributeArray.get( (int) value.getValueCopy()));
				
		case argumentValueByName:
			return findValue(arguments, (String) value.getValueCopy());
			
		case branching:
			tmp = expression1.evaluateExpression(arguments);
			if (tmp.isTrue()  ) 
				tmp =  expression2.evaluateExpression(arguments);
			else
				tmp = expression3.evaluateExpression(arguments);
			return tmp;
			
		case condition:
			
			switch (operator) {
			case or:
				return calculation.or(
						expression1.evaluateExpression(arguments) ,
						expression2.evaluateExpression(arguments)   );
			case and:
				return calculation.and(
						expression1.evaluateExpression(arguments) ,
						expression2.evaluateExpression(arguments)   );
			default:
				return calculation.createValue(Type.bool,  false);
			}
			
			
		case comparison :
			
			switch (operator) {
			case equal:
				return calculation.compareEqual(
						expression1.evaluateExpression(arguments) ,
						expression2.evaluateExpression(arguments)   );
			case notEqual:
				return calculation.compareNotEqual(
						expression1.evaluateExpression(arguments) ,
						expression2.evaluateExpression(arguments)   );
			case greater:
				return calculation.compareGreater(
						expression1.evaluateExpression(arguments) ,
						expression2.evaluateExpression(arguments)   );
			case greaterEqual:
				return calculation.compareGreaterEqual(
						expression1.evaluateExpression(arguments) ,
						expression2.evaluateExpression(arguments)   );
			case less:
				return calculation.compareLess(
						expression1.evaluateExpression(arguments) ,
						expression2.evaluateExpression(arguments)   );
			case lessEqual:
				return calculation.compareLessEqual(
						expression1.evaluateExpression(arguments) ,
						expression2.evaluateExpression(arguments)   );
			default:
				return calculation.createValue(Type.bool,  false);
			}
			
		case addition:
			return calculation.addition(
					expression1.evaluateExpression(arguments) ,
					expression2.evaluateExpression(arguments)   );
			
		case multiplication:
			return calculation.multiplication(
					expression1.evaluateExpression(arguments) ,
					expression2.evaluateExpression(arguments)   );
			
		case replacement:
			//TODO
			
			return Calculation.getNothing();
			
		case function:
			Value calculateArguments[] = new Value[3];
			calculateArguments[0] = expression1.evaluateExpression(arguments);
			calculateArguments[1] = expression2.evaluateExpression(arguments);
			calculateArguments[2] = expression3.evaluateExpression(arguments);
			return function.calculate(calculateArguments);
			
		default:
			return Calculation.getNothing();
		}
		


	}
	

	private Object get(Value[] arguments, Type type, int wantedOccurence) {
		int argumentsCount;
		int occurence;
		
		argumentsCount = arguments.length;
		occurence = 0;
		
		for (int index = 0; index < argumentsCount; index++) {
			if (arguments[index].getType() == type) {
				occurence++;
				if (occurence == wantedOccurence) {
					return arguments[index].getValue();
				}
			}
		}
		return null;
	}
	
	private Value findValue(Value[] arguments, String name) {
		int argumentsCount;
		int index;
		
		argumentsCount = arguments.length;
		
		for (index = 0; index < argumentsCount; index++) {
			if (arguments[index].getName() == name) return arguments[index];
		}
		
		return new Value();

	}

}
