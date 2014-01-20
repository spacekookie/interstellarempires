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

import static com.badlogic.gdx.graphics.g2d.SpriteBatch.C1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.C2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.C3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.C4;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.U1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.U2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.U3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.U4;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.V1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.V2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.V3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.V4;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.X1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.X2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.X3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.X4;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.Y1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.Y2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.Y3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.Y4;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;

/**
 * Adapted from the LibGDX HexTiledMapRenderer
 * 
 * @author Katharina
 * 
 * */
public class HexMapRenderer extends BatchTiledHexMapRenderer {
  private boolean yDown = false;
  private float tileSizeX, tileSizeY;
  private Set<HexMapLayer> nodes;

  {
	nodes = new HashSet<HexMapLayer>();
  }

  public boolean isYdown() {
	return yDown;
  }

  public void setYDown(boolean yDown) {
	this.yDown = yDown;
  }

  public HexMapRenderer(HexTileMap map) {
	super(map);
  }

  public HexMapRenderer(HexTileMap map, float unitScale) {
	super(map, unitScale);
  }

  public HexMapRenderer(HexTileMap map, SpriteBatch spriteBatch) {
	super(map, spriteBatch);
  }

  public HexMapRenderer(HexTileMap map, float unitScale, SpriteBatch spriteBatch) {
	super(map, unitScale, spriteBatch);
  }

  private float[] vertices = new float[20];

  public void renderTileLayer(HexMapLayer layer) {
	final Color batchColor = spriteBatch.getColor();
	final float color = Color.toFloatBits(batchColor.r, batchColor.g, batchColor.b, batchColor.a * layer.getOpacity());

	final int layerWidth = layer.getWidth();
	final int layerHeight = layer.getHeight();

	final float layerTileWidth = layer.getTileWidth() * unitScale;
	final float layerTileHeight = layer.getTileHeight() * unitScale;

	this.tileSizeX = layerTileWidth;
	this.tileSizeY = layerTileHeight;

	final float layerTileWidth25 = layerTileWidth * 0.25f;
	final float layerTileWidth75 = layerTileWidth * 0.75f;

	final float layerTileHeight50 = layerTileHeight * 0.50f;
	final float layerTileHeight150 = layerTileHeight * 1.50f;

	final int col1 = Math.max(0, (int) (((viewBounds.x - layerTileWidth25) / layerTileWidth75)));
	final int col2 = Math.min(layerWidth, (int) ((viewBounds.x + viewBounds.width + layerTileWidth75) / layerTileWidth75));

	final int row1 = Math.max(0, (int) ((viewBounds.y / layerTileHeight150)));
	final int row2 = Math.min(layerHeight, (int) ((viewBounds.y + viewBounds.height + layerTileHeight150) / layerTileHeight));

	final float[] vertices = this.vertices;

	for (int row = row1; row < row2; row++) {
	  for (int col = col1; col < col2; col++) {
		float x = layerTileWidth75 * col;
		float y = (col % 2 == (yDown ? 0 : 1) ? 0 : layerTileHeight50) + (layerTileHeight * row);

		final HexCell cell = (HexCell) layer.getCell(col, row);
		nodes.add(layer);
		if (cell == null) {
		  x += layerTileWidth;
		  continue;
		}
		final TiledMapTile tile = cell.getTile();
		if (tile != null) {
		  if (tile instanceof AnimatedTiledMapTile)
			continue;

		  final boolean flipX = cell.getFlipHorizontally();
		  final boolean flipY = cell.getFlipVertically();
		  final int rotations = cell.getRotation();

		  TextureRegion region = tile.getTextureRegion();

		  float x1 = x;
		  float y1 = y;
		  float x2 = x1 + region.getRegionWidth() * unitScale;
		  float y2 = y1 + region.getRegionHeight() * unitScale;

		  float u1 = region.getU();
		  float v1 = region.getV2();
		  float u2 = region.getU2();
		  float v2 = region.getV();

		  vertices[X1] = x1;
		  vertices[Y1] = y1;
		  vertices[C1] = color;
		  vertices[U1] = u1;
		  vertices[V1] = v1;

		  vertices[X2] = x1;
		  vertices[Y2] = y2;
		  vertices[C2] = color;
		  vertices[U2] = u1;
		  vertices[V2] = v2;

		  vertices[X3] = x2;
		  vertices[Y3] = y2;
		  vertices[C3] = color;
		  vertices[U3] = u2;
		  vertices[V3] = v2;

		  vertices[X4] = x2;
		  vertices[Y4] = y1;
		  vertices[C4] = color;
		  vertices[U4] = u2;
		  vertices[V4] = v1;

		  if (flipX) {
			float temp = vertices[U1];
			vertices[U1] = vertices[U3];
			vertices[U3] = temp;
			temp = vertices[U2];
			vertices[U2] = vertices[U4];
			vertices[U4] = temp;
		  }
		  if (flipY) {
			float temp = vertices[V1];
			vertices[V1] = vertices[V3];
			vertices[V3] = temp;
			temp = vertices[V2];
			vertices[V2] = vertices[V4];
			vertices[V4] = temp;
		  }
		  if (rotations == 2) {
			float tempU = vertices[U1];
			vertices[U1] = vertices[U3];
			vertices[U3] = tempU;
			tempU = vertices[U2];
			vertices[U2] = vertices[U4];
			vertices[U4] = tempU;
			float tempV = vertices[V1];
			vertices[V1] = vertices[V3];
			vertices[V3] = tempV;
			tempV = vertices[V2];
			vertices[V2] = vertices[V4];
			vertices[V4] = tempV;
			break;
		  }
		  spriteBatch.draw(region.getTexture(), vertices, 0, 20);
		}
	  }
	}

  }

  /** TODO: Improve collision detection accuracy!!! */
  public HexCell getTileWithPos(float ix, float iy) {
	int x = Math.round(ix);
	int y = Math.round(iy);

	int tileCol = Math.max((int) Math.floor(x / 112), 0);
	int tileRow = Math.max((int) Math.floor(y / 97), 0);

	for (HexMapLayer l : nodes) {
	  HexCell cell = l.getCell(tileRow, tileCol);
	  if (cell != null)
		return cell;
	}

	return null;
  }

  @Override
  public void renderObject(MapObject object) {

  }

}
