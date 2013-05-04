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

import client.core.ScreenHandler;
import client.screens.TestScreen;
import client.settings.Settings;
import client.types.IntVec2;
import client.util.Find;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;

import framework.map.SolarSystem;
import framework.objects.GameObject;
import framework.objects.Star.StarType;

/**
 * Counterpart to the @HexMap. Will display a solarsystem to the player. Extending the Group instead of the Actor to hold own
 * Actor instances
 * 
 * @author ***REMOVED***
 * 
 */
public class SolarMap extends Group implements Disposable {

	private IntVec2 tileID;
	private SolarSystem system;
	private TextureAtlas atlas;
	private TextureRegion star;
	private ShapeRenderer renderer;
	private int offset;
	private StarType starType;

		/**
		 * Stuff that needs to be done no matter what constructor is called ^_^
		 */
		{
			offset = 100;
			renderer = new ShapeRenderer();
			atlas = new TextureAtlas(Gdx.files.internal("assets/solar/prot-solarsystem-icons.pack"));
			star = atlas.findRegion("prot-star-browndwarf"); // TODO: Change this to respond to the @starType.
		}

	/**
	 * 
	 * @param TileID
	 *         The id of the tile in the map as a 2-dimensional vector
	 * @param system
	 *         The absolute solarsystem data from the framework.
	 */
	public SolarMap(IntVec2 tileID, SolarSystem solar) {
		this.tileID = tileID;
		if (solar != null)
			{
				system = solar;

				starType = solar.getStar().getType();

			} else
			{
				Gdx.app.log(Settings.LOG, "FATAL ERROR RECEIVING MAP INFORMATION: " + tileID);
			}
	}

	public SolarMap(IntVec2 tileID) {
		this.tileID = tileID;
	}

	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.end();
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		renderer.translate(getX() / 2, getY() / 2, 0);

		renderer.begin(ShapeType.Circle);

		renderer.circle((Gdx.graphics.getWidth() / 2) - offset, Gdx.graphics.getHeight() / 2, system.getRadius());
		renderer.end();
		batch.begin();

		switch (starType) {
			case BROWNDWARF:
				batch.draw(star, Find.getCenter().x - 25 - offset, Find.getCenter().y - 25, 0, 0, 50, 50, 1, 1, 0);
				break;

			case BLUEGIANT:
				batch.draw(star, Find.getCenter().x - 25 - offset, Find.getCenter().y - 25, 0, 0, 75, 75, 1, 1, 0);
				break;

			default:
				break;
		}

	}

	/**
	 * Will be called to update the map view with current data.
	 * 
	 * @param o
	 *         A set of all @GameObject instances in the system.
	 */
	public void updateData(Set<GameObject> o) {

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
