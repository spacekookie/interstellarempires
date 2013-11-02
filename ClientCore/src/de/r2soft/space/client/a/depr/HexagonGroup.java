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

package de.r2soft.space.client.a.depr;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

import de.r2soft.space.framework.map.Map;
import de.r2soft.space.framework.players.Player;

@Deprecated
public class HexagonGroup extends Group {

	/** Rendering */
	private ShapeRenderer shapeRenderer;
	private Vector2 size;
	private float offsetX;
	private boolean transform = true;

	/** Empty constructor */
	public HexagonGroup(float x, float y, float offsetX) {
		shapeRenderer = new ShapeRenderer();
		size = new Vector2(x, y);
		this.offsetX = offsetX;
	}

	@Deprecated
	public HexagonGroup(Vector2 position, Map map, Player player) {

	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.end();
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(75, 500, 0);
		// shapeRenderer.begin(ShapeType.Rectangle);
		shapeRenderer.rect(0, -size.y, size.x, size.y);
		shapeRenderer.end();
		batch.begin();
		if (transform)
			applyTransform(batch, computeTransform());
		drawChildren(batch, parentAlpha);
		if (transform)
			resetTransform(batch);
	}

}
