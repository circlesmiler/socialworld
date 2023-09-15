/*
* Social World
* Copyright (C) 2023  Mathias Sikos
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
package org.socialworld.objects.enums;

public enum EnumFood {

	ignore("ignore"),
	Fruit("org.socialworld.objects.concrete.eatable.Fruit"),
	Apple("org.socialworld.objects.concrete.eatable.fruits.Apple"),
	Banana("org.socialworld.objects.concrete.eatable.fruits.Banana");
	
	private String className;

	private EnumFood(String className) {
		this.className = className;
	}
	
	/**
	 * The method returns the enum name which belongs to the parameter className
	 * 
	 * @param className
	 * @return EnumFood name
	 */
	public static EnumFood getName(String className) {
		for (EnumFood value : EnumFood.values())
			if (value.className == className)
				return value;
		return ignore;
	}
}
