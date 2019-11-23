package org.socialworld.objects.concrete.eatable.fruits;

import org.socialworld.attributes.properties.NutrientProperty;
import org.socialworld.attributes.properties.TasteProperty;
import org.socialworld.objects.concrete.eatable.Fruit;
import org.socialworld.objects.properties.IEatable;
import org.socialworld.objects.properties.IThrowable;

public class Apple extends Fruit implements IEatable, IThrowable {

	NutrientProperty nutrientProps;
	TasteProperty tasteProps;
	
	public Apple(int objectID) {
		super(objectID);
	}
	
	public  float getTemperature() {return 20; };
	public  float getConsistence() {return 20; };
	public  float getFirmness() {return 20; };
	

	public float getGrip() { return 20; }
	public float getMass()  { return 20; }
	public float getVolume()  { return 20; }
	public float getForm()  { return 20; }

}
