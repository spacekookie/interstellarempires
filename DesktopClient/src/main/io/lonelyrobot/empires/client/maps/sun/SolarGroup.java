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

package io.lonelyrobot.empires.client.maps.sun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

import io.lonelyrobot.empires.client.resources.Assets;
import io.lonelyrobot.empires.client.resources.Values;
import io.lonelyrobot.empires.client.util.Find;
import io.lonelyrobot.empires.framework.map.SolarSystem;

/**
 * REPLACE WITH SOLAR RENDERER!
 * 
 * @author ***REMOVED***
 * 
 */
@Deprecated
public class SolarGroup extends Group {

	/** Rendering */
	private ShapeRenderer shapeRenderer;
	private float offsetX;
	private boolean transform = true;
	private SolarSystem system;
	private Vector2 position;

	public SolarGroup(SolarSystem system, float x, float y, float offsetX) {
		shapeRenderer = new ShapeRenderer();
		this.offsetX = offsetX - (offsetX / 6);
		position = new Vector2(x, y);
		this.system = system;
	}

	@Deprecated
	public SolarGroup(float x, float y, float offsetX, Vector2 vec) {
		shapeRenderer = new ShapeRenderer();
		this.offsetX = offsetX;
		this.position = vec;
	}

	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.end();
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX() / 2, getY() / 2, 0);
		// shapeRenderer.begin(ShapeType.Circle);
		shapeRenderer.circle((Gdx.graphics.getWidth() / 2) + offsetX, Gdx.graphics.getHeight() / 2,
				(float) system.getRadius());
		shapeRenderer.circle((Gdx.graphics.getWidth() / 2) + offsetX, Gdx.graphics.getHeight() / 2,
				(float) (system.getRadius() + 25));
		shapeRenderer.end();
		batch.begin();
		applyTransform(batch, computeTransform());
		drawChildren(batch, parentAlpha);

		// switch (system.getStar().getClassification()) {
		// case BROWNDWARF:
		// batch.draw(Assets.STARS_BROWN_DWARF, Find.getCenter().x - (Values.SIZE_CELESTIAL_BROWN_DWARF
		// / 2) + offsetX, Find.getCenter().y
		// - (Values.SIZE_CELESTIAL_BROWN_DWARF / 2), 0, 0, Values.SIZE_CELESTIAL_BROWN_DWARF,
		// Values.SIZE_CELESTIAL_BROWN_DWARF, 1, 1, 0);
		// break;
		//
		// case BLUEGIANT:
		// batch.draw(Assets.STARS_BLUE_GIANT, Find.getCenter().x - (Values.SIZE_CELESTIAL_BLUE_GIANT /
		// 2) + offsetX, Find.getCenter().y
		// - (Values.SIZE_CELESTIAL_BLUE_GIANT / 2), 0, 0, Values.SIZE_CELESTIAL_BLUE_GIANT,
		// Values.SIZE_CELESTIAL_BLUE_GIANT, 1, 1, 0);
		// break;
		//
		// case NEUTRON:
		// batch.draw(Assets.STARS_BLUE_DWARF, Find.getCenter().x - (Values.SIZE_CELESTIAL_BLUE_DWARF /
		// 2) + offsetX, Find.getCenter().y
		// - (Values.SIZE_CELESTIAL_BLUE_DWARF / 2), 0, 0, Values.SIZE_CELESTIAL_BLUE_DWARF,
		// Values.SIZE_CELESTIAL_BLUE_DWARF, 1, 1, 0);
		// break;
		//
		// case REDDWARF:
		// batch.draw(Assets.STARS_RED_DWARF, Find.getCenter().x - (Values.SIZE_CELESTIAL_RED_DWARF / 2)
		// + offsetX, Find.getCenter().y
		// - (Values.SIZE_CELESTIAL_RED_DWARF / 2), 0, 0, Values.SIZE_CELESTIAL_RED_DWARF,
		// Values.SIZE_CELESTIAL_RED_DWARF, 1, 1, 0);
		// break;
		//
		// case REDGIANT:
		// batch.draw(Assets.STARS_RED_GIANT, Find.getCenter().x - (Values.SIZE_CELESTIAL_RED_GIANT / 2)
		// + offsetX, Find.getCenter().y
		// - (Values.SIZE_CELESTIAL_RED_GIANT / 2), 0, 0, Values.SIZE_CELESTIAL_RED_GIANT,
		// Values.SIZE_CELESTIAL_RED_GIANT, 1, 1, 0);
		// break;
		//
		// default:
		// break;
		// }

		// DRAW END

	}

}
