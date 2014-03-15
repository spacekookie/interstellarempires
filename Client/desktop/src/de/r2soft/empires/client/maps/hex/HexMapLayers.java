/* #########################################################################
 * Copyright (c) 2014 Random Robot Softworks
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

package de.r2soft.space.client.maps.hex;

import java.util.Iterator;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.reflect.ClassReflection;

public class HexMapLayers implements Iterable<HexMapLayer> {
  private Array<HexMapLayer> layers = new Array<HexMapLayer>();

  /**
   * @param index
   * @return layer at index
   */
  public HexMapLayer get(int index) {
	return layers.get(index);
  }

  /**
   * @param name
   * @return first layer matching the name, null otherwise
   */
  public MapLayer get(String name) {
	for (MapLayer layer : layers) {
	  if (name.equals(layer.getName())) {
		return layer;
	  }
	}
	return null;
  }

  /** @return number of layers in the collection */
  public int getCount() {
	return layers.size;
  }

  /**
   * @param layer
   *          layer to be added to the set
   */
  public void add(HexMapLayer layer) {
	this.layers.add(layer);
  }

  /**
   * @param index
   *          removes layer at index
   */
  public void remove(int index) {
	layers.removeIndex(index);
  }

  /**
   * @param layer
   *          layer to be removed
   */
  public void remove(HexMapLayer layer) {
	layers.removeValue(layer, true);
  }

  /**
   * @param type
   * @return array with all the layers matching type
   */
  public <T extends HexMapLayer> Array<T> getByType(Class<T> type) {
	return getByType(type, new Array<T>());
  }

  /**
   * 
   * @param type
   * @param fill
   *          array to be filled with the matching layers
   * @return array with all the layers matching type
   */
  public <T extends HexMapLayer> Array<T> getByType(Class<T> type, Array<T> fill) {
	fill.clear();
	for (MapLayer layer : layers) {
	  if (ClassReflection.isInstance(type, layer)) {
		fill.add((T) layer);
	  }
	}
	return fill;
  }

  /**
   * @return iterator to set of layers
   */
  @Override
  public Iterator<HexMapLayer> iterator() {
	return layers.iterator();
  }

}