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

package de.r2soft.empires.client.maps.hex;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

public abstract class BatchTiledHexMapRenderer implements TiledHexMapRenderer, Disposable {
  protected HexTileMap map;

  protected float unitScale;

  protected SpriteBatch spriteBatch;

  protected Rectangle viewBounds;

  protected boolean ownsSpriteBatch;

  public HexTileMap getMap() {
	return map;
  }

  public void setMap(HexTileMap map) {
	this.map = map;
  }

  public float getUnitScale() {
	return unitScale;
  }

  public SpriteBatch getSpriteBatch() {
	return spriteBatch;
  }

  public Rectangle getViewBounds() {
	return viewBounds;
  }

  public BatchTiledHexMapRenderer(HexTileMap map) {
	this(map, 1.0f);
  }

  public BatchTiledHexMapRenderer(HexTileMap map, float unitScale) {
	this.map = map;
	this.unitScale = unitScale;
	this.viewBounds = new Rectangle();
	this.spriteBatch = new SpriteBatch();
	this.ownsSpriteBatch = true;
  }

  public BatchTiledHexMapRenderer(HexTileMap map, SpriteBatch spriteBatch) {
	this(map, 1.0f, spriteBatch);
  }

  public BatchTiledHexMapRenderer(HexTileMap map, float unitScale, SpriteBatch spriteBatch) {
	this.map = map;
	this.unitScale = unitScale;
	this.viewBounds = new Rectangle();
	this.spriteBatch = spriteBatch;
	this.ownsSpriteBatch = false;
  }

  @Override
  public void setView(OrthographicCamera camera) {
	spriteBatch.setProjectionMatrix(camera.combined);
	float width = camera.viewportWidth * camera.zoom;
	float height = camera.viewportHeight * camera.zoom;
	viewBounds.set(camera.position.x - width / 2, camera.position.y - height / 2, width, height);
  }

  @Override
  public void setView(Matrix4 projection, float x, float y, float width, float height) {
	spriteBatch.setProjectionMatrix(projection);
	viewBounds.set(x, y, width, height);
  }

  @Override
  public void render() {
	beginRender();
	for (HexMapLayer layer : map.getHexLayers()) {
	  if (layer.isVisible()) {
		if (layer instanceof HexMapLayer) {
		  renderTileLayer((HexMapLayer) layer);
		}
		else {
		  for (MapObject object : layer.getObjects()) {
			renderObject(object);
		  }
		}
	  }
	}
	endRender();
  }

  @Override
  public void render(int[] layers) {
	beginRender();
	for (int layerIdx : layers) {
	  HexMapLayer layer = map.getHexLayers().get(layerIdx);
	  if (layer.isVisible()) {
		if (layer instanceof HexMapLayer) {
		  renderTileLayer((HexMapLayer) layer);
		}
		else {
		  for (MapObject object : layer.getObjects()) {
			renderObject(object);
		  }
		}
	  }
	}
	endRender();
  }

  /** Called before the rendering of all layers starts. */
  protected void beginRender() {
	AnimatedTiledMapTile.updateAnimationBaseTime();
	spriteBatch.begin();
  }

  /** Called after the rendering of all layers ended. */
  protected void endRender() {
	spriteBatch.end();
  }

  @Override
  public void dispose() {
	if (ownsSpriteBatch) {
	  spriteBatch.dispose();
	}
  }
}
