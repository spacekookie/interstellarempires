/* 
 * Copyright (c) 2013 Katharina Fey
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

import client.objects.actors.GenericMapObject;
import client.settings.Settings;
import client.types.IntVec2;
import client.util.Find;
import client.util.ResPack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

import framework.map.SolarSystem;
import framework.objects.Star.STARTYPE;
import framework.objects.Unit;

/**
 * Counterpart to the @HexMap. Will display a solarsystem to the player. Extending the Group instead
 * of the Actor to hold own Actor instances
 * 
 * @author Katharina
 * 
 */
public class SolarMap extends Group implements Disposable {

	private IntVec2 tileID;
	private SolarSystem system;
	private ShapeRenderer renderer;
	private int offset;
	private STARTYPE starType;
	private Stage stage;
	private Set<Unit> units;
	private boolean selected;
	private Set<GenericMapObject> localGameObjects;

	private Sprite sprite;

		/**
		 * Stuff that needs to be done no matter what constructor is called ^_^
		 */
		{
			offset = 100;
			renderer = new ShapeRenderer();
		}

	/**
	 * 
	 * @param TileID
	 *         The id of the tile in the map as a 2-dimensional vector
	 * @param system
	 *         The absolute solarsystem data from the framework.
	 */
	public SolarMap(IntVec2 tileID, SolarSystem solar, Set<GenericMapObject> localGameObjects) {
		this.tileID = tileID;
		this.localGameObjects = localGameObjects;
		if (solar != null)
			{
				system = solar;
				starType = solar.getStar().getType();
				units = solar.getUnits();

			} else
			{
				Gdx.app.log(Settings.LOG, "FATAL ERROR RECEIVING MAP INFORMATION: " + tileID);
			}
	}

	public SolarMap(IntVec2 tileID) {
		this.tileID = tileID;
	}

	public void setInputToChild() {
		Gdx.input.setInputProcessor(stage);
	}

	public void draw(SpriteBatch batch, float parentAlpha) {

		if (stage == null)
			stage = new Stage();
		stage.clear();

		for (GenericMapObject object : localGameObjects)
			{
				stage.addActor(object);
			}

		stage.draw();

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
				batch.draw(ResPack.STAR_BROWN_DWARF, Find.getCenter().x - offset - (ResPack.STAR_BROWN_DWARF_SIZE / 2),
						Find.getCenter().y - (ResPack.STAR_BROWN_DWARF_SIZE / 2), (ResPack.STAR_BROWN_DWARF_SIZE / 2),
						(ResPack.STAR_BROWN_DWARF_SIZE / 2), ResPack.STAR_BROWN_DWARF_SIZE, ResPack.STAR_BROWN_DWARF_SIZE, 1, 1, 0);
				break;

			case BLUEGIANT:
				batch.draw(ResPack.STAR_NEUTRON, Find.getCenter().x - offset - (ResPack.STAR_BLUE_GIANT_SIZE / 2),
						Find.getCenter().y - (ResPack.STAR_BLUE_GIANT_SIZE / 2), (ResPack.STAR_BLUE_GIANT_SIZE / 2),
						(ResPack.STAR_BLUE_GIANT_SIZE / 2), ResPack.STAR_BLUE_GIANT_SIZE, ResPack.STAR_BLUE_GIANT_SIZE, 1, 1, 0);
				break;

			case NEUTRON:
				batch.draw(ResPack.STAR_NEUTRON, Find.getCenter().x - offset - (ResPack.STAR_BLUE_DWARF_SIZE / 2),
						Find.getCenter().y - (ResPack.STAR_BLUE_DWARF_SIZE / 2), (ResPack.STAR_BLUE_DWARF_SIZE / 2),
						(ResPack.STAR_BLUE_DWARF_SIZE / 2), ResPack.STAR_BLUE_DWARF_SIZE, ResPack.STAR_BLUE_DWARF_SIZE, 1, 1, 0);
				break;

			case REDDWARF:
				batch.draw(ResPack.STAR_RED_DWARF, Find.getCenter().x - offset - (ResPack.STAR_RED_DWARF_SIZE / 2),
						Find.getCenter().y - (ResPack.STAR_RED_DWARF_SIZE / 2), (ResPack.STAR_RED_DWARF_SIZE / 2),
						(ResPack.STAR_RED_DWARF_SIZE / 2), ResPack.STAR_RED_DWARF_SIZE, ResPack.STAR_RED_DWARF_SIZE, 1, 1, 0);
				break;

			case REDGIANT:
				batch.draw(ResPack.STAR_RED_DWARF, Find.getCenter().x - offset - (ResPack.STAR_RED_GIANT_SIZE / 2),
						Find.getCenter().y - (ResPack.STAR_RED_GIANT_SIZE / 2), (ResPack.STAR_RED_GIANT_SIZE / 2),
						(ResPack.STAR_RED_GIANT_SIZE / 2), ResPack.STAR_RED_GIANT_SIZE, ResPack.STAR_RED_GIANT_SIZE, 1, 1, 0);
				break;

			default:
				break;
		}

		// if (tileID.equals(new IntVec2(1, 0)))

		/** Draw units in units-set */
		// for (Unit item : units)
		// {
		// float x = (float) item.getPosition().getX();
		// float y = (float) item.getPosition().getY();
		// stage.addActor(new GenericMapObject(x, y, item.getType(), item.getFlag(), item.getClaim(),
		// ALLEGIANCE.FRIENDLY));
		//
		// }

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
