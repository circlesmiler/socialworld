/**
 * 
 */
package org.socialworld.core;

import org.socialworld.attributes.ActionMode;
import org.socialworld.attributes.ActionType;
import org.socialworld.attributes.Direction;
import org.socialworld.attributes.Time;
import org.socialworld.objects.SimulationObject;

/**
 * The class is an implementation of the abstract class AbstractAction. It adds
 * methods for manipulating the duration of an action.
 * 
 * @author Mathias Sikos (tyloesand)
 */
public class Action extends AbstractAction {

/**
 * The constructor creates an action object with its attributes.
 * @param type
 * @param mode
 * @param target
 * @param direction
 * @param intensity
 * @param minTime
 * @param maxTime
 * @param priority
 * @param duration
 */	
	public Action(final ActionType type, final ActionMode mode,
			final SimulationObject target, final Direction direction,
			final double intensity, final Time minTime, final Time maxTime,
			final int priority, final long duration) {
		this.setType(type);
		this.setMode(mode);
		this.setTarget(target);
		this.setDirection(direction);
		this.setIntensity(intensity);
		this.setMinTime(minTime);
		this.setMaxTime(maxTime);
		this.setPriority(priority);
		this.setDuration(duration);

	}
	
	/**
	 * The method decrements the attribute remainedDuration. That means, an
	 * action needs less time to complete.
	 * 
	 * @param decrement
	 */
	public void lowerRemainedDuration(final long decrement) {
		this.remainedDuration -= decrement;
	}

	/**
	 * The method increments the attribute remainedDuration. That means, an
	 * action needs more time to complete.
	 * 
	 * @param increment
	 */
	public void raiseRemainedDuration(final long increment) {
		this.remainedDuration += increment;
	}
}
