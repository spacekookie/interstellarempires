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

import java.util.HashMap;

import org.apache.commons.math3.util.Pair;

import de.r2soft.space.framework.objects.GameObject.Category;
import de.r2soft.space.framework.objects.GameObject.Type;

public class ValueManager {

  private static final ValueManager instance = new ValueManager();
  private HashMap<GameObject.Type, Float> hitpoints;
  private HashMap<GameObject.Type, Float> armour;
  private HashMap<GameObject.Type, Float> damage;
  private HashMap<GameObject.Type, Float> speed;
  private HashMap<GameObject.Type, Float> mass;
  private HashMap<GameObject.Type, Float> size;
  private HashMap<GameObject.Type, Pair<Integer, Type>> slots;
  private HashMap<GameObject.Type, GameObject.Category> category;

  private ValueManager() {
	// Initialize values
	category.put(Type.FIGHTER_I, Category.SHIP);
	damage.put(Type.FIGHTER_I, 10f);

  }

  /**
   * Get an instance of the ValueManager
   * 
   * @return
   */
  public static ValueManager getInstance() {
	return instance;
  }

  public Float getDamage(GameObject.Type type) {
	Float dmg = damage.get(type);

	if (dmg != null)
	  return dmg;
	else
	  return 0f;
  }

  public Category getCategory(Type type) {
	return category.get(type);
  }

}
