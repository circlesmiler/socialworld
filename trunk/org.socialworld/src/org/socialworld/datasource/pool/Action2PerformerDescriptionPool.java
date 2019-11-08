package org.socialworld.datasource.pool;

import java.util.ArrayList;
import java.util.List;

import org.socialworld.actions.ActionMode;
import org.socialworld.attributes.Attribute;
import org.socialworld.calculation.Expression;
import org.socialworld.calculation.FunctionByExpression;
import org.socialworld.calculation.Value;
import org.socialworld.calculation.descriptions.Action2PerformerDescription;
import org.socialworld.calculation.expressions.Calculate;
import org.socialworld.calculation.expressions.Nothing;

public class Action2PerformerDescriptionPool extends DescriptionPool {

	
	

	private static Action2PerformerDescriptionPool instance;
	
	private Action2PerformerDescription descriptions[];
	
	private Action2PerformerDescriptionPool () {
		
		sizeDescriptionsArray = ActionMode.maxIndex() + 1;
		descriptions = new Action2PerformerDescription[sizeDescriptionsArray];
		
		initialize();
		
	}
	
	public static Action2PerformerDescriptionPool getInstance() {
		if (instance == null) {
			instance = new Action2PerformerDescriptionPool();
		}
		return instance;
	}

	public Action2PerformerDescription getDescription(ActionMode actionMode ) {
		int index;
		Action2PerformerDescription description = null;
		
		index = actionMode.getIndex() ;
		
		if (index >= 0 & sizeDescriptionsArray > index) 
			description = descriptions[index];
		else
			// create a dummy description with an expression that returns the invalid "nothing" value
			description = new Action2PerformerDescription();
		
		return description;
	}

	@Override
	protected void initialize() {

		Action2PerformerDescription description;
		List<FunctionByExpression> expressions;
		
		for (int index = 0; index < sizeDescriptionsArray; index++) {
			
			if (ActionMode.getName(index) == ActionMode.ignore) {
				continue;
			}
			else {
				
				expressions = initializeWithTestData(ActionMode.getName(index));
				
				if (expressions.size() > 0) {
					
					description = new Action2PerformerDescription();
					
					for (int i = 0; i < expressions.size(); i++) {
						description.addFunction(expressions.get(i));
					}
					descriptions[index] = description;
					
				}
				else {
					// empty description
					descriptions[index] = new Action2PerformerDescription();
				}
			}
	
		}

	}
	
	private List<FunctionByExpression> initializeWithTestData(ActionMode actionMode) {
		
		Expression startExpression = Nothing.getInstance();
		List<FunctionByExpression> result;
		result = new ArrayList<FunctionByExpression>();
		
		switch (actionMode)
		{
			case walk:
			case run:
			case sneak:
			case jump:
			case swim:
			case fly:
				
				// directionAction
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_DIRECTION + ")", Value.VALUE_BY_NAME_ACTION_DIRECTION );
				result.add( new FunctionByExpression(startExpression) );
	
				// acceleration
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_MOVE_ACCELERATION + ")", Value.VALUE_BY_NAME_ACTION_MOVE_ACCELERATION);
				result.add( new FunctionByExpression(startExpression) );

				// velocity
				startExpression = new Calculate("ADD(" +
													"GET(" + Value.VALUE_BY_NAME_ACTION_MOVE_VELOCITY + ")" + "," +
													"GET(" + Value.VALUE_BY_NAME_ACTION_MOVE_ACCELERATION + ")" +
												")" , Value.VALUE_BY_NAME_ACTION_MOVE_VELOCITY);
				result.add( new FunctionByExpression(startExpression) );
				
				break;
		
			case hand:
			case foot:
	
				// directionAction
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_DIRECTION + ")", Value.VALUE_BY_NAME_ACTION_DIRECTION );
				result.add( new FunctionByExpression(startExpression) );
	
				// actorsIntensity
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_INTENSITY + ")", Value.VALUE_BY_NAME_ACTION_INTENSITY);
				result.add( new FunctionByExpression(startExpression) );
	
				// intensity
				startExpression = new Calculate("MUL(" +
													"GET(" + Value.VALUE_BY_NAME_ACTION_INTENSITY + ")" + "," +
													"ATTR(" + Attribute.power.getIndex() + ")" +
												")" , Value.VALUE_BY_NAME_EVENT_INTENSITY);
				result.add( new FunctionByExpression(startExpression) );
							
				break;
		
			case pull:
			case push:
				
				// directionAction
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_DIRECTION + ")", Value.VALUE_BY_NAME_ACTION_DIRECTION );
				result.add( new FunctionByExpression(startExpression) );

				// actorsIntensity
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_INTENSITY + ")", Value.VALUE_BY_NAME_ACTION_INTENSITY);
				result.add( new FunctionByExpression(startExpression) );

				// intensity
				startExpression = new Calculate("MUL(" +
													"GET(" + Value.VALUE_BY_NAME_ACTION_INTENSITY + ")" + "," +
													"ATTR(" + Attribute.power.getIndex() + ")" +
												")" , Value.VALUE_BY_NAME_EVENT_INTENSITY);
				result.add( new FunctionByExpression(startExpression) );
				
				// Item1
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_ITEM1 + ")", Value.VALUE_BY_NAME_ACTION_ITEM1);
				result.add( new FunctionByExpression(startExpression) );

				break;
				
			case useItemLeftHand:
			case useItemRightHand:

				// directionAction
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_DIRECTION + ")", Value.VALUE_BY_NAME_ACTION_DIRECTION );
				result.add( new FunctionByExpression(startExpression) );

				// actorsIntensity
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_INTENSITY + ")", Value.VALUE_BY_NAME_ACTION_INTENSITY);
				result.add( new FunctionByExpression(startExpression) );

				// intensity
				startExpression = new Calculate("MUL(" +
													"GET(" + Value.VALUE_BY_NAME_ACTION_INTENSITY + ")" + "," +
													"ATTR(" + Attribute.power.getIndex() + ")" +
												")" , Value.VALUE_BY_NAME_EVENT_INTENSITY);
				result.add( new FunctionByExpression(startExpression) );
				
				// Item1
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_ITEM1 + ")", Value.VALUE_BY_NAME_ACTION_ITEM1);
				result.add( new FunctionByExpression(startExpression) );
				
				break;
				
			case useTwoItems:
			case combineItems_AddRightToLeft:
			case combineItems_AddLeftToRight:
				
				// directionAction
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_DIRECTION + ")", Value.VALUE_BY_NAME_ACTION_DIRECTION );
				result.add( new FunctionByExpression(startExpression) );

				// actorsIntensity
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_INTENSITY + ")", Value.VALUE_BY_NAME_ACTION_INTENSITY);
				result.add( new FunctionByExpression(startExpression) );

				// intensity
				startExpression = new Calculate("MUL(" +
													"GET(" + Value.VALUE_BY_NAME_ACTION_INTENSITY + ")" + "," +
													"ATTR(" + Attribute.power.getIndex() + ")" +
												")" , Value.VALUE_BY_NAME_EVENT_INTENSITY);
				result.add( new FunctionByExpression(startExpression) );
				
				// Item1
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_ITEM1 + ")", Value.VALUE_BY_NAME_ACTION_ITEM1);
				result.add( new FunctionByExpression(startExpression) );

				// Item2
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_ITEM2 + ")", Value.VALUE_BY_NAME_ACTION_ITEM2);
				result.add( new FunctionByExpression(startExpression) );

				break;
				
			case punchRightFistStraight:
				
				
				// directionHit
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_SIMOBJ_DIRECTION_VIEW + ")", Value.VALUE_BY_NAME_EVENT_DIRECTION );
				result.add( new FunctionByExpression(startExpression) );

				// actorsIntensity
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_INTENSITY + ")", Value.VALUE_BY_NAME_ACTION_INTENSITY);
				result.add( new FunctionByExpression(startExpression) );

				// intensity
				startExpression = new Calculate("MUL(" +
													"GET(" + Value.VALUE_BY_NAME_ACTION_INTENSITY + ")" + "," +
													"ATTR(" + Attribute.power.getIndex() + ")" +
												")" , Value.VALUE_BY_NAME_EVENT_INTENSITY);
				result.add( new FunctionByExpression(startExpression) );

				// directionChest
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_SIMOBJ_DIRECTION_CHEST + ")", Value.VALUE_BY_NAME_SIMOBJ_DIRECTION_CHEST);
				result.add( new FunctionByExpression(startExpression) );

				// directionView
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_SIMOBJ_DIRECTION_VIEW + ")", Value.VALUE_BY_NAME_SIMOBJ_DIRECTION_VIEW);
				result.add( new FunctionByExpression(startExpression) );
				
				break;
				
			case weaponClub:
				
				// directionHit
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_SIMOBJ_DIRECTION_VIEW + ")", Value.VALUE_BY_NAME_EVENT_DIRECTION );
				result.add( new FunctionByExpression(startExpression) );

				// actorsIntensity
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_INTENSITY + ")", Value.VALUE_BY_NAME_ACTION_INTENSITY);
				result.add( new FunctionByExpression(startExpression) );

				// intensity
				startExpression = new Calculate("MUL(" +
													"GET(" + Value.VALUE_BY_NAME_ACTION_INTENSITY + ")" + "," +
													"ATTR(" + Attribute.power.getIndex() + ")" +
												")" , Value.VALUE_BY_NAME_EVENT_INTENSITY);
				result.add( new FunctionByExpression(startExpression) );

				// weapon
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_WEAPON + ")", Value.VALUE_BY_NAME_ACTION_WEAPON);
				result.add( new FunctionByExpression(startExpression) );

				// directionChest
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_SIMOBJ_DIRECTION_CHEST + ")", Value.VALUE_BY_NAME_SIMOBJ_DIRECTION_CHEST);
				result.add( new FunctionByExpression(startExpression) );

				// directionView
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_SIMOBJ_DIRECTION_VIEW + ")", Value.VALUE_BY_NAME_SIMOBJ_DIRECTION_VIEW);
				result.add( new FunctionByExpression(startExpression) );
				
				break;
				
			case listenTo:
			case understand:
				
				// Sentence
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_SENTENCE + ")", Value.VALUE_BY_NAME_ACTION_SENTENCE);
				result.add( new FunctionByExpression(startExpression) );
				
				// Target
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_ACTION_TARGET + ")", Value.VALUE_BY_NAME_ACTION_TARGET);
				result.add( new FunctionByExpression(startExpression) );

				// directionView
				startExpression = new Calculate("GET(" + Value.VALUE_BY_NAME_SIMOBJ_DIRECTION_VIEW + ")", Value.VALUE_BY_NAME_SIMOBJ_DIRECTION_VIEW);
				result.add( new FunctionByExpression(startExpression) );

				break;
				
			default:
				break;
				
		}
		
		return result;
		
	}
	

	
}