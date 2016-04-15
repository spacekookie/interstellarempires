/* #########################################################################
 * Copyright (c) 2013 Random Robot Softworks
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 ######################################################################### */
package de.r2soft.empires.framework.objects;

public class Star extends BaseObject {

	private String alias;

	/** Creates a new star with a Star Type */
	public Star(Type type) {
		super.setType(type);
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return super.getType().toString() + " — " + alias;
	}

	/**
	 * @param type
	 *          The type of star we're dealing with here
	 */

	/**
	 * This will compute the base size of the star based on it's type and a random multiplier. Will
	 * return basic values with multiplier = 1
	 */
	private void computeStarRadius(double multi) {
		// TODO: Actually compute size :(
	}

}
