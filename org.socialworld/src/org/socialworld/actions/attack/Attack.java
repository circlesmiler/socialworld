/*
* Social World
* Copyright (C) 2015  Mathias Sikos
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
package org.socialworld.actions.attack;

import org.socialworld.actions.ActionPerformer;
import org.socialworld.attributes.Attribute;
import org.socialworld.calculation.Type;
import org.socialworld.calculation.Value;
import org.socialworld.calculation.Vector;
import org.socialworld.objects.Animal;
import org.socialworld.objects.Human;

/**
 * German:
 * Die Klasse Attack ist von der abstrakten Klasse ActionPerformer abgeleitet.
  * 
 * Die Klasse Attack dient der Wirksamwerdung der Aktion,
 *  n�mlich als Argument f�r das zur Aktion geh�rende Ereignis.
 *
 *  In der Ausf�hrungsmethode perform() werden f�r den Fall eines Waffenangriffs/Faustschlags
 *   - die Richtung des Angriffs
 *   - die Intensit�t des Akteurs
 *   - die Intensit�t des Akteurs auf globaler Skala
 *   - die Waffe (das als Waffe verwendete Objekt)
 *   - die Richtung (Ausrichtung) der Brust
 *   - die Blickrichtung
 *   f�r den Standardzugriff aus dem Ereignis heraus bereitgestellt.
 *   
*  In der Ausf�hrungsmethode perform() werden f�r den Fall eines tierischen Angriffs
 *   - die Richtung des Angriffs
 *   - die Intensit�t des Akteurs
 *   - die Intensit�t des Akteurs auf globaler Skala
 *   f�r den Standardzugriff aus dem Ereignis heraus bereitgestellt.
 *   
 * 
 * @author Mathias Sikos
 *
 */
public class Attack extends ActionPerformer {

// TODO implementation methods	performFightAttack() and performAnimalAttack() (until now very very simple)
	
 	public  Attack (ActionAttack action) {
 		super(action);
 	}
 	
  
   	public void perform() {
		
		if (!isValid()) {
	
			if (isHumanFightAttack())
				performFightAttack();
			else
				performAnimalAttack();
			
		}
		
	}

   	private void performFightAttack() {
		ActionAttack originalAction;
		final Human actor;
		
		IWeapon weapon;
		float actorsIntensity;
		float intensity;
		int actorsPower;
		
		Vector directionChest;
		Vector directionView;
		Vector directionHit;
	
		originalAction  = (ActionAttack) getOriginalActionObject();
		actor = (Human) originalAction.getActor();
		
		actorsPower = actor.getAttributes().get(Attribute.power);
			
		// to do
	  	directionChest = actor.getDirectionChest();
		directionView = actor.getDirectionView();
		directionHit = actor.getDirectionView();

		// to do
		actorsIntensity = originalAction.getIntensity();
		intensity = actorsIntensity * actorsPower;
	
		weapon = originalAction.getWeapon();
	
		addParam( new Value(Type.vector, "direction", directionHit));
		addParam( new Value(Type.floatingpoint, "actorsIntensity", actorsIntensity));
		addParam( new Value(Type.floatingpoint, "intensity", intensity));
		addParam( new Value(Type.simulationObject, "weapon", weapon));
		addParam( new Value(Type.vector, "directionChest", directionChest));
		addParam( new Value(Type.vector, "directionView", directionView));
		
		setValid();
  		
   	}
   	
   	private void performAnimalAttack() {
		ActionAttack originalAction;
		final Animal actor;
		
		float actorsIntensity;
		float intensity;
		int actorsPower;
		
		Vector directionHit;
	
		originalAction  = (ActionAttack) getOriginalActionObject();
		actor = (Animal) originalAction.getActor();
		
		actorsPower = actor.getAttributes().get(Attribute.power);
			
		// to do
		directionHit = actor.getDirectionView();

		// to do
		actorsIntensity = originalAction.getIntensity();
		intensity = actorsIntensity * actorsPower;
	
		addParam( new Value(Type.vector, "directionHit", directionHit));
		addParam( new Value(Type.floatingpoint, "actorsIntensity", actorsIntensity));
		addParam( new Value(Type.floatingpoint, "intensity", intensity));
		
		setValid();
  		
   	}
   	
   	private boolean isHumanFightAttack() {
		ActionAttack originalAction;
		originalAction = (ActionAttack) getOriginalActionObject();
		
		switch (originalAction.getType()) {
		case useWeapon:
		case punch:
			return true;
		default:
			return false;
			
		}
   	}
}
