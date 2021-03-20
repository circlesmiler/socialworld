package org.socialworld.objects.concrete.animals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.socialworld.attributes.ISavedValues;
import org.socialworld.attributes.PropertyName;
import org.socialworld.calculation.SimulationCluster;
import org.socialworld.calculation.Type;
import org.socialworld.calculation.ValueProperty;
import org.socialworld.objects.State;
import org.socialworld.tools.Generation;

public class StateBody extends State {

	public static final String VALUENAME_FACE_COLOUR = "faceColour";
	public static final String VALUENAME_HAIR_COLOUR = "hairColour";

	public static final String METHODNAME_GET_FACE_COLOUR = "getFaceColour";
	public static final String METHODNAME_GET_HAIR_COLOUR = "getHairColour";

	///////////////////////////////////////////////////////////////////////////////////////////
	//////////////////static instance for meta information    ///////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////

	private static StateBody singletonDummyForGenerationTools;
	private static List<String> listOfReturnableGetPropertyTypes;
	private boolean listOfReturnablePropertyTypesIsFilled = false;
	private static String[] returnableGetPropertyTypes = new String[]{} ;
	
	public static StateBody getInstance(Generation calledFromGeneration) {
		if (singletonDummyForGenerationTools == null) {
			singletonDummyForGenerationTools = new StateBody(calledFromGeneration);
		}
		return singletonDummyForGenerationTools;
	}
	
	private StateBody(Generation calledFromGeneration) 
	{
		
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	////////////////// creating instance for simulation    ///////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////
	
	public StateBody() {
		super();
	}

	protected  void initPropertyName() {
		setPropertyName(PropertyName.stateBody);
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////  implementing  ISavedValues  ////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public ISavedValues copyForProperty(SimulationCluster cluster) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValueProperty getProperty(SimulationCluster cluster, PropertyName propName, String valueName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setProperty(PropertyName propName, ValueProperty property) {
		// TODO Auto-generated method stub

	}

	public List<String> getReturnableGetPropertyTypes() {
		if (!listOfReturnablePropertyTypesIsFilled) {
			List<String> result = super.getReturnableGetPropertyTypes();
			for (int indexAdd = 0; indexAdd < returnableGetPropertyTypes.length; indexAdd++) {
				result.add(returnableGetPropertyTypes[indexAdd]);
			}
			listOfReturnableGetPropertyTypes = result;
			listOfReturnablePropertyTypesIsFilled = true;
		}
		return new ArrayList<String>(listOfReturnableGetPropertyTypes);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////  implementing  StateBody methods  ////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////
	
	protected ValueProperty getFaceColour() {
		return new ValueProperty(Type.integer,  VALUENAME_FACE_COLOUR, Color.PINK.getRGB());
	}

	protected ValueProperty getHairColour() {
		return new ValueProperty(Type.integer,  VALUENAME_HAIR_COLOUR, Color.BLACK.getRGB());
	}
	
}