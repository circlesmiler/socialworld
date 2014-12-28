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
package org.socialworld.attributes;

/**
 * The enumeration holds all attribute names and collects an index for every
 * attribute. So an attribute is addressable in arrays.
 * 
 * @author Mathias Sikos (tyloesand)
 */
public enum Attribute {
	mood(0), courage(1), morals(2), materialism(3), tiredness(4),
	curiosity(5), spirituality(6), hunger(7);

	/**
	 * The constant holds the informations how many attributes are simulated.
	 * The constant is used for iteration about all attributes and for creation
	 * of attribute arrays.
	 */
	public static final int NUMBER_OF_ATTRIBUTES = 8;
	
	private int arrayIndex;

	private Attribute(int index) {
		this.arrayIndex = index;
	}

	/**
	 * The method returns the index of the attribute.
	 * 
	 * @return attribute's index
	 */
	public int getIndex() {
		return arrayIndex;
	}

	/**
	 * The method returns the attribute name which belongs to the parameter
	 * attribute index.
	 * 
	 * @param arrayIndex
	 *            attribute index
	 * @return attribute name
	 */
	public Attribute getAttributeName(int arrayIndex) {
		for (Attribute attribute : Attribute.values())
			if (attribute.arrayIndex == arrayIndex)
				return attribute;
		return null;
	}
}