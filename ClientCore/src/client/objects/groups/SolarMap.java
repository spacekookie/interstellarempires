/* 
 * Copyright (c) 2013 ***REMOVED***
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
 */
package client.objects.groups;

import java.util.Set;

import framework.map.SolarSystem;
import framework.objects.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;

/**
 * Counterpart to the @HexMap. Will display a solarsystem to the player.
 * Extending the Group instead of the Actor to hold own Actor instances
 * 
 * @author ***REMOVED***
 * 
 */
public class SolarMap extends Group implements Disposable {

	private Vector2 tileID;
	private SolarSystem system;
	private TextureAtlas atlas;
	private TextureRegion fleet;
	private ShapeRenderer renderer;

	/**
	 * 
	 * @param TileID
	 *            The id of the tile in the map as a 2-dimensional vector
	 * @param system
	 *            The absolute solarsystem data from the framework.
	 */
	public SolarMap(Vector2 tileID, SolarSystem system) {
		this.tileID = tileID;
		this.system = system;
		renderer = new ShapeRenderer();
	}

	public SolarMap(Vector2 tileID) {
		this.tileID = tileID;
		atlas = new TextureAtlas(
				Gdx.files.internal("assets/solar/prot-solarsystem-icons.pack"));
		fleet = atlas.findRegion("prot-fleet-fighter-player");
		renderer = new ShapeRenderer();
	}

	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.end();
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		// renderer.translate(getX() / 2, getY() / 2, 0);

		renderer.begin(ShapeType.Circle);
		renderer.circle(500, 400, 500); // TODO: Get solarsystem radius
		// from Framework. Somehow.
		renderer.end();
		batch.begin();

		if (tileID.equals(new Vector2(0, 0)))
			batch.draw(fleet, 200, 200, 200, 200, 128, 128, 1, 1, 0);
	}

	/**
	 * Will be called to update the map view with current data.
	 * 
	 * @param o
	 *            A set of all @GameObject instances in the system.
	 */
	public void updateData(Set<GameObject> o) {

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}