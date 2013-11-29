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
package de.r2soft.space.framework.objects;

import com.badlogic.gdx.math.Vector2;

import de.r2soft.space.framework.objects.factory.UnitFactory;
import de.r2soft.space.framework.players.Player;

public class Structure extends PlayerObject {

  /** The type of station */
  public static enum StationType {
	IHUB, FACTORY_SMALL, FACTORY_CAPITAL, MILITARY_SMALL;
  }

  private UnitFactory factory;

  public Structure(SuperType superType, StationType type, Player claim, Vector2 position) {
	super.setSuperclass(superType);

	/** Should the structure have a unit factory? */
	if (type.equals(StationType.FACTORY_SMALL) || type.equals(StationType.FACTORY_CAPITAL) || type.equals(StationType.MILITARY_SMALL)) {
	  factory = new UnitFactory(claim, this);
	}

	super.setClaim(claim);
	super.setPosition(position);
  }

  /** Check if the structure has a UnitFactory */
  public boolean hasFactory() {
	return factory == null ? false : true;
  }

  /** @return UnitFactory if exists. Can be null */
  public UnitFactory getFactory() {
	return hasFactory() ? factory : null;
  }
}
