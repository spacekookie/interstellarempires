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

package de.r2soft.space.client.maps.hex;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class HexTileMap extends Map {
  private TiledMapTileSets tilesets;
  private Array<? extends Disposable> ownedResources;
  private HexMapLayers layers = new HexMapLayers();

  /**
   * @return collection of tilesets for this map
   */
  public TiledMapTileSets getTileSets() {
	return tilesets;
  }

  /**
   * Creates empty TiledMap
   */
  public HexTileMap() {
	tilesets = new TiledMapTileSets();
  }

  public HexMapLayers getHexLayers() {
	return layers;
  }

  /**
   * Used by loaders to set resources when loading the map directly, without {@link AssetManager}. To be disposed in {@link #dispose()}.
   * 
   * @param resources
   */
  public void setOwnedResources(Array<? extends Disposable> resources) {
	this.ownedResources = resources;
  }

  @Override
  public void dispose() {
	if (ownedResources != null) {
	  for (Disposable resource : ownedResources) {
		resource.dispose();
	  }
	}
  }
}