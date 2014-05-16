/* #########################################################################
 * Copyright (c) 2014 RANDOM ROBOT SOFTWORKS
 * (See @authors file)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ######################################################################### */
package de.r2soft.empires.client.maps.hex;

import com.badlogic.gdx.maps.Map;

/**
 * 
 * @author: Katharina Fey <kookie@spacekookie.de>
 */
public class R2HexMap extends Map {

  /** Holds all the Cells. Replace this with cooler data type? */
  private R2HexCell[][] cells;

  /** Width and height set in the beginning of the rendering */
  private int width, height;

  /** Width and height of a tile. */
  private int tileWidth, tileHeight;

  public R2HexMap(int width, int height, int tileWidth, int tileHeight) {
	this.width = width;
	this.height = height;
	this.tileWidth = tileWidth;
	this.tileHeight = tileHeight;
	this.cells = new R2HexCell[width][height];

  }

  /**
   * @param x
   * @param y
   * @return cell at (x, y)
   */
  public R2HexCell getCell(int x, int y) {
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
  public void setCell(int x, int y, R2HexCell cell) {
	if (x < 0 || x >= width)
	  return;
	if (y < 0 || y >= height)
	  return;
	cells[x][y] = cell;
  }

  public int getTileWidth() {
	return tileWidth;
  }

  public void setTileWidth(int tileWidth) {
	this.tileWidth = tileWidth;
  }

  public int getTileHeight() {
	return tileHeight;
  }

  public void setTileHeight(int tileHeight) {
	this.tileHeight = tileHeight;
  }

  public int getWidth() {
	return width;
  }

  public int getHeight() {
	return height;
  }

}
