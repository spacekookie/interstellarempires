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

  /**
   * Holds all possible star types for the ENTIRE game to use. No pressure. Don't add more candy, it'll just become fat.
   */
  public enum StarType {
	BROWNDWARF("Brown"), REDDWARF("SmallRed"), YELLOWDWARF("SmallYellow"), WHITEDWARF("White"), REDGIANT("BigRed"), BLUEGIANT("BigBlue"), NEUTRON(
		"Neutron"), BLACKHOLE("Black"), GIANTSPACEPUDDING("Nom");

	private final String alias;

	private StarType(final String alias) {
	  this.alias = alias;
	}

	@Override
	public String toString() {
	  return alias;
	}

  }

  private StarType classification;

  /**
   * master constructor to create a star with its type
   * 
   * @param type
   *          The Type of the star
   */
  public Star(StarType classification) {
	this.classification = classification;
	this.computeStarRadius(1);
  }

  /**
   * @param type
   *          The type of star we're dealing with here
   */
  public void setClassification(StarType classification) {
	this.classification = classification;
  }

  /** @return: the stars classification */
  public StarType getClassification() {
	return classification;
  }

  /** This will compute the base size of the star based on it's type and a random multiplier. Will return basic values with multiplier = 1 */
  private void computeStarRadius(double multi) {
	// TODO: Actually compute size :(
  }

}
