/* 
 * Copyright (c) 2012 Leander Sabel
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
 */

package de.r2soft.space.framework.objects;

public class Star extends GameObject {

	/** Holds all possible star types for the ENTIRE game to use. No pressure. Don't add more candy, it'll just become fat */
	public enum STARTYPE {
		BROWNDWARF, REDDWARF, WHITEDWARF, REDGIANT, BLUEGIANT, NEUTRON, BLACKHOLE, GIANTSPACEPUDDING;
	}

	private STARTYPE type;

	/** @return: the stars type */
	public STARTYPE getType() {
		return type;
	}

	/**
	 * @param type
	 *         The type of star we're dealing with here
	 */
	public void setType(STARTYPE type) {
		this.type = type;
	}

}
