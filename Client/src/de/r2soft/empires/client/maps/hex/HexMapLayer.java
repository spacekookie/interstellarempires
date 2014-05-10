/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

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

package de.r2soft.empires.client.maps.hex;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * 2R Softworks adaptation of the TiledMapTileLayer from the LibGDX framework
 * */
public class HexMapLayer extends MapLayer {
  private int width;
  private int height;

  private float tileWidth;
  private float tileHeight;

  private HexCell[][] cells;

  /**
   * @return layer's witdth in tiles
   */
  public int getWidth() {
	return width;
  }

  /**
   * @return layer's height in tiles
   */
  public int getHeight() {
	return height;
  }

  /**
   * @return tiles' width in pixels
   */
  public float getTileWidth() {
	return tileWidth;
  }

  /**
   * @return tiles' height in pixels
   */
  public float getTileHeight() {
	return tileHeight;
  }

  /**
   * Creates TiledMap layer
   * 
   * @param width
   *          layer width in tiles
   * @param height
   *          layer height in tiles
   * @param tileWidth
   *          tile width in pixels
   * @param tileHeight
   *          tile height in pixels
   */
  public HexMapLayer(int width, int height, int tileWidth, int tileHeight) {
	super();
	this.width = width;
	this.height = height;
	this.tileWidth = tileWidth;
	this.tileHeight = tileHeight;
	this.cells = new HexCell[width][height];
  }

  /**
   * @param x
   * @param y
   * @return cell at (x, y)
   */
  public HexCell getCell(int x, int y) {
	if (x < 0 || x >= width)
	  return null;
	if (y < 0 || y >= height)
	  return null;
	return cells[x][y];
  }

  /**
   * Sets the {@link HexCell} at the given coordinates.
   * 
   * @param x
   * @param y
   * @param cell
   */
  public void setCell(int x, int y, HexCell cell) {
	if (x < 0 || x >= width)
	  return;
	if (y < 0 || y >= height)
	  return;
	cells[x][y] = cell;
  }

}
