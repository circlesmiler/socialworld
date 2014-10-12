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
public abstract class Expression {
	int ID;
	
	ExpressionFunction function;
	ConditionOperator operator;

	int attributeIndex;
	
	Object value;
	Object constant;
	Object result;
	
	Expression expressionForTrue;
	Expression expressionForFalse;
	int IDTrue;
	int IDFalse;
	
	public Expression() {
		function = ExpressionFunction.identity;
	}
	
	
	public void setID(int ID) {
		this.ID = ID;
	}

	public void setID(String ID) {
		this.ID = (int) Float.parseFloat(ID);
	}

	public int getID() {return ID; };

	public void setIDTrue(int ID) {
		this.IDTrue = ID;
	}

	public void setIDTrue(String ID) {
		this.IDTrue = (int) Float.parseFloat(ID);
	}
	
	public int getIDTrue() {return IDTrue; };
	
	public void setIDFalse(int ID) {
		this.IDFalse = ID;
	}

	public void setIDFalse(String ID) {
		this.IDFalse = (int) Float.parseFloat(ID);
	}

	public int getIDFalse() {return IDFalse; };

	public void setFunction(ExpressionFunction function) {
		this.function = function;
	}

	public void setOperator(ConditionOperator operator) {
		this.operator = operator;
	}

	public void setConstant(Object constant) {
		this.constant = constant;
	}


	public void setAttributeIndex(int index) {
		this.attributeIndex = index;
	}

	public void setAttributeIndex(String index) {
		this.attributeIndex = (int) Float.parseFloat(index);
	}

	public void setTrueExpression(Expression expressionForTrue) {
		this.expressionForTrue = expressionForTrue;
	}

	public void setFalseExpression(Expression expressionForFalse) {
		this.expressionForFalse = expressionForFalse;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	protected Object getValue() {
		return value;
	}
	
	protected abstract void addition();
	protected abstract void multiplication();
	protected abstract void replacement();
	protected abstract void identity();
	protected abstract void defaultFunction();
	
	//protected abstract void evaluateSubExpression(AttributeArray attributeArray, boolean conditionIsTrue);
	protected void evaluateSubExpression(AttributeArray attributeArray, boolean conditionIsTrue) {
	
		
		if (conditionIsTrue)
			result =  expressionForTrue.evaluateExpression(attributeArray,  value);
		else
			result = expressionForFalse.evaluateExpression(attributeArray,  value);
	}

	/**
	 * The method evaluates the expression by calling the  method evaluateFunction().
	 * The method evaluateFunction() finally calculates the reaction's concrete property
	 * by calling the concrete calculation methods.
	 */
	public Object evaluateExpression(AttributeArray attributeArray, Object value) {
		
		this.value = value;
		
		evaluateFunction(attributeArray);
		return result;
	}

	/**
	 * The method evaluates the expression by calling the parent method evaluateFunction().
	 * The method evaluateFunction() finally calculates the reaction's duration
	 * by calling the calculation methods.
	 */
	public Object evaluateExpression(AttributeArray attributeArray) {
		
		evaluateFunction(attributeArray);
		return result;
	}

	/**
	 * The method evaluates the expression function. 
	 * If the expression function is a comparison
	 * the comparison is evaluated. If the comparison result is evaluated to
	 * true the true-expression is called recursively. In other case the
	 * false-expression is called recursively. If the expression is an addition,
	 * a multiplication or a replacement the attribute value is changed by the
	 * according operation. 
	 */
	protected void evaluateFunction(AttributeArray attributeArray) {
		int operandValue;
		boolean conditionIsTrue = false;

		switch (this.function) {
		case condition:
			operandValue = attributeArray.get(this.attributeIndex);

			switch (this.operator) {
			case equal:
				if (operandValue == (int) this.constant)
					conditionIsTrue = true;
				break;
			case notEqual:
				if (operandValue != (int) this.constant)
					conditionIsTrue = true;
				break;
			case less:
				if (operandValue < (int) this.constant)
					conditionIsTrue = true;
				break;
			case lessEqual:
				if (operandValue <= (int) this.constant)
					conditionIsTrue = true;
				break;
			case greaterEqual:
				if (operandValue >= (int) this.constant)
					conditionIsTrue = true;
				break;
			case greater:
				if (operandValue > (int) this.constant)
					conditionIsTrue = true;
				break;
			}

			evaluateSubExpression(attributeArray, conditionIsTrue);
			
			break;
		case addition:
			addition();
			break;
		case multiplication:
			multiplication();
			break;
		case replacement:
			replacement();
			break;
		case identity:
			identity();
			break;
		default:
			defaultFunction();
		}


	}
}
