package org.socialworld.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;

import org.socialworld.calculation.*;
import org.socialworld.attributes.ActionMode;
import org.socialworld.attributes.ActionType;
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
		
		index = eventType * Event.MAX_EVENT_TYPE + reactionType;
		
		if (sizeDescriptionsArray > index) 			description = descriptions[index];
		return description;
	}

	protected void initialize() {
		initializeFromFile();
	}
	
	private void initializeFromFile() {
		
		Expression expression;
		
		EventReactionDescription erd;
		
		int index;
		int eventType = 0;
		int reactionType = 0;
		

		// temporary initialized with ActionDelayExpression
		// expression and erd must be initialized
		expression = new ActionDelayExpression();
		erd = new EventReactionDescription();
		
		try
		{
			String line;
			
	
			InputStream input = new URL("http://sourceforge.net/projects/socialworld/files/hmn_erd.swp").openStream();
			LineNumberReader lnr
			   = new LineNumberReader(new InputStreamReader(input));

	
			while ((line = lnr.readLine()) != null)
			{

				if (line.startsWith("<ERD>")) {
					erd = new EventReactionDescription();
					continue;
				}

				if (line.startsWith("</ERD>")) {
					index = eventType * Event.MAX_EVENT_TYPE + reactionType;
					descriptions[index]= erd;
					continue;
				}
				
				if (line.startsWith("<ID>")) {
					line = line.substring(4);
					line = line.replace("</ID>", "");
					line = line.trim();
					expression.setID(line);
					continue;
				}

				if (line.startsWith("<IDTrue>")) {
					line = line.substring(8);
					line = line.replace("</IDTrue>", "");
					line = line.trim();
					expression.setIDTrue(line);
					continue;
				}

				if (line.startsWith("<IDFalse>")) {
					line = line.substring(9);
					line = line.replace("</IDFalse>", "");
					line = line.trim();
					expression.setIDFalse(line);
					continue;
				}

				if (line.startsWith("<Fct>"))
				{
					line = line.substring(5);
					line = line.replace("</Fct>", "");
					line = line.trim();
					switch(line) {
						case "condition":  expression.setFunction(ExpressionFunction.condition);break;
						case "addition":  expression.setFunction(ExpressionFunction.addition);break;
						case "multiplication":  expression.setFunction(ExpressionFunction.multiplication);break;
						case "replacement":  expression.setFunction(ExpressionFunction.replacement);break;
						case "identity":  expression.setFunction(ExpressionFunction.identity);break;
					}
					continue;
				}

				if (line.startsWith("<Op>"))
				{
					line = line.substring(4);
					line = line.replace("</Op>", "");
					line = line.trim();
					switch(line) {
						case "equal":  expression.setOperator(ConditionOperator.equal);break;
						case "notEqual":  expression.setOperator(ConditionOperator.notEqual);break;
						case "less":  expression.setOperator(ConditionOperator.less);break;
						case "lessEqual":  expression.setOperator(ConditionOperator.lessEqual);break;
						case "greaterEqual":  expression.setOperator(ConditionOperator.greaterEqual);break;
						case "greater":  expression.setOperator(ConditionOperator.greater);break;
					}
					continue;
				}

				if (line.startsWith("<Const>"))
				{
					line = line.substring(7);
					line = line.replace("</Const>", "");
					line = line.trim();
					// TODO cast line from String to concrete datatype
					expression.setConstant(line);
					continue;
				}

				if (line.startsWith("<AttrId>"))
				{
					line = line.substring(8);
					line = line.replace("</AttrId>", "");
					line = line.trim();
					expression.setAttributeIndex(line);
					continue;
				}


				if (line.startsWith("<ActionDelayExp>")) {
					expression = new ActionDelayExpression();
					continue;
				}

				if (line.startsWith("</ActionDelayExp>")) {
					addExpression(expression);
					erd.setDelayExpression((ActionDelayExpression) expression);
					continue;
				}

				if (line.startsWith("<ActionDurationExp>")) {
					expression = new ActionDurationExpression();
					continue;
				}

				if (line.startsWith("</ActionDurationExp>")) {
					addExpression(expression);
					erd.setDurationExpression((ActionDurationExpression) expression);
					continue;
				}

				if (line.startsWith("<ActionIntensityExp>")) {
					expression = new ActionIntensityExpression();
					continue;
				}

				if (line.startsWith("</ActionIntensityExp>")) {
					addExpression(expression);
					erd.setIntensityExpression((ActionIntensityExpression) expression);
					continue;
				}

				if (line.startsWith("<ActionModeExp>")) {
					expression = new ActionModeExpression();
					continue;
				}
				
				if (line.startsWith("<Mode>"))
				{
					ActionModeExpression tmp;
					
					tmp = (ActionModeExpression) expression;
					
					line = line.substring(6);
					line = line.replace("</Mode>", "");
					line = line.trim();
					switch(line) {
						case "run":  tmp.setValue(ActionMode.run);break;
						case "walk":  tmp.setValue(ActionMode.walk);break;
						case "jump":  tmp.setValue(ActionMode.jump);break;
						case "sleepIntentioned":  tmp.setValue(ActionMode.sleepIntentioned);break;
						case "sleepCaused":  tmp.setValue(ActionMode.sleepCaused);break;
						case "weaponRightHand":  tmp.setValue(ActionMode.weaponRightHand);break;
						case "weaponLeftHand":  tmp.setValue(ActionMode.weaponLeftHand);break;
						case "examineItem":  tmp.setValue(ActionMode.examineItem);break;
						case "takeItem":  tmp.setValue(ActionMode.takeItem);break;
						case "useItem":  tmp.setValue(ActionMode.useItem);break;
						case "collectItem":  tmp.setValue(ActionMode.collectItem);break;
						case "switchItemToLeftHand":  tmp.setValue(ActionMode.switchItemToLeftHand);break;
						case "useTwoItems":  tmp.setValue(ActionMode.useTwoItems);break;
						case "dropItem":  tmp.setValue(ActionMode.dropItem);break;
						case "say":  tmp.setValue(ActionMode.say);break;
						case "scream":  tmp.setValue(ActionMode.scream);break;
						case "whisper":  tmp.setValue(ActionMode.whisper);break;
					}
					continue;
				}
				
				if (line.startsWith("</ActionModeExp>")) {
					addExpression(expression);
					erd.setActionModeExpression((ActionModeExpression) expression);
					continue;
				}

				if (line.startsWith("<ActionPriorityExp>")) {
					expression = new ActionPriorityExpression();
					continue;
				}

				if (line.startsWith("</ActionPriorityExp>")) {
					addExpression(expression);
					erd.setPriorityExpression((ActionPriorityExpression) expression);
					continue;
				}

				if (line.startsWith("<ActionRelDirExp>")) {
					expression = new ActionRelativeDirectionExpression();
					continue;
				}

				if (line.startsWith("<Vector>"))
				{
					ActionRelativeDirectionExpression tmp;
					
					tmp = (ActionRelativeDirectionExpression) expression;
					
					line = line.substring(8);
					line = line.replace("</Vector>", "");
					line = line.trim();
					tmp.setValue(new Vector(line));
					continue;
				}

				if (line.startsWith("</ActionRelDirExp>")) {
					addExpression(expression);
					erd.setRelativeDirectionExpression((ActionRelativeDirectionExpression) expression);
					continue;
				}

				if (line.startsWith("<ActionTypeExp>")) {
					expression = new ActionTypeExpression();
					continue;
				}
				
				if (line.startsWith("<Type>"))
				{
					ActionTypeExpression tmp;
					
					tmp = (ActionTypeExpression) expression;
					
					line = line.substring(6);
					line = line.replace("</Type>", "");
					line = line.trim();
					switch(line) {
						case "touch":  tmp.setValue(ActionType.touch);break;
						case "sleep":  tmp.setValue(ActionType.sleep);break;
						case "changeMove":  tmp.setValue(ActionType.changeMove);break;
						case "kick":  tmp.setValue(ActionType.kick);break;
						case "controlHandManually":  tmp.setValue(ActionType.controlHandManually);break;
						case "spell":  tmp.setValue(ActionType.spell);break;
						case "useWeaponLeft":  tmp.setValue(ActionType.useWeaponLeft);break;
						case "useWeaponRight":  tmp.setValue(ActionType.useWeaponRight);break;
						case "move":  tmp.setValue(ActionType.move);break;
						case "say":  tmp.setValue(ActionType.say);break;
						case "handleItem":  tmp.setValue(ActionType.handleItem);break;
					}
					continue;
				}

				if (line.startsWith("</ActionTypeExp>")) {
					addExpression(expression);
					erd.setActionTypeExpression((ActionTypeExpression) expression);
				continue;
				}

				if (line.startsWith("<EventType>"))
				{
					line = line.substring(11);
					line = line.replace("</EventType>", "");
					line = line.trim();
					eventType = (int) Float.parseFloat(line);
					erd.setEventType(eventType);
					continue;
				}

				if (line.startsWith("<ReactionType>"))
				{
					line = line.substring(14);
					line = line.replace("</ReactionType>", "");
					line = line.trim();
					reactionType = (int) Float.parseFloat(line);
					erd.setReactionType(reactionType);
					continue;
				}

			}
			lnr.close();
		}
		catch (IOException e)
		{
			System.out.println("Fehler beim Lesen der Datei");
			e.printStackTrace();
		}
		
		setTrueAndFalseExpressions();
	}

}
