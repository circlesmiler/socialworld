package org.socialworld.calculation.expressions;

import org.socialworld.attributes.SimPropertyName;
import org.socialworld.calculation.Expression;
import org.socialworld.calculation.Expression_Function;
import org.socialworld.calculation.Type;
import org.socialworld.calculation.Value;

public class GetValue extends Expression {

	public static String GETVALUE = "GETVal";
	public static String GETPROPERTY = "GETProp";
	public static String GETFUNCTIONVALUE = "GETFctVal";
	
	public GetValue(String getValuePath, String valueAliasName) {
		
		super();
		
		String steps[];
		steps = getValuePath.split(".");
		
		if (steps.length > 0) {
			setOperation(Expression_Function.branching);
			
			Expression exp1 = new Constant( new Value(Type.bool,  true));
			Expression exp2 = new GetValue(steps, 0, valueAliasName);
			Expression exp3 = Nothing.getInstance();
			
			setExpression1(exp1);
			setExpression2(exp2);
			setExpression3(exp3);
			setValid();
			
		}
		else {
			setOperation(Expression_Function.nothing);
		}
			
	}
	
	private GetValue(String[] steps, int indexContinue, String valueAliasName) {
		
		Expression exp1 = Nothing.getInstance();
		Expression exp2 = Nothing.getInstance();
		String step;
		
		step = steps[indexContinue];
		exp1 = getStepExpression(step, valueAliasName);

		if (steps.length == indexContinue + 1) {
			// that is the value from expression 1,
			// because the expression 1 evaluation result
			// is forwarded (as argument value list array with that one element)  to expression 2 evaluation
			exp2 = new GetArgumentByName(valueAliasName, valueAliasName);
		}
		
		if (steps.length == indexContinue + 2) {
			step = steps[indexContinue + 1];
			exp2 = getStepExpression(step, valueAliasName);
		}
		else {
				
			exp2 = new GetValue(steps, indexContinue + 1, valueAliasName);

		}

		setOperation(Expression_Function.get);
		setExpression1(exp1);
		setExpression2(exp2);
		setExpression3(Nothing.getInstance());
		
		setValid();

	}
	
	public static String getValue(SimPropertyName prop) {
		return GETVALUE + "(" + prop.toString() + ")"; 
	}
	
	public static String getValue(String name) {
		return GETVALUE + "(" + name + ")"; 
	}

	public static String getProperty(SimPropertyName prop) {
		return GETPROPERTY + "(" + prop.toString() + ")"; 
	}
	
	public static String getProperty(String name) {
		return GETPROPERTY + "(" + name + ")"; 
	}

	public static String getFctValue(String name) {
		return GETFUNCTIONVALUE + "(" + name + ")"; 
	}
	
	private Expression getStepExpression(String step, String valueAliasName) {
		Expression result = Nothing.getInstance();
		String name;
		
		name = step.substring(step.indexOf("(") + 1, step.indexOf(")"));
		if (step.indexOf(GETVALUE + "(") > 0 ) {
			result = new GetArgumentByName(name, valueAliasName);
		}
		if (step.indexOf(GETPROPERTY + "(") > 0 ) {
			result = new GetProperty(SimPropertyName.forString(name), valueAliasName);
		}
		if (step.indexOf(GETFUNCTIONVALUE + "(") > 0 ) {
			result = new GetProperty(name, valueAliasName);
		}

		return result;
	}

}