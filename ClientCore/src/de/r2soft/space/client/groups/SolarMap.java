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
package de.r2soft.space.client.groups;

import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

import de.r2soft.space.client.actors.GenericMapObject;
import de.r2soft.space.client.util.Find;
import de.r2soft.space.client.util.ResPack;
import de.r2soft.space.framework.map.SolarSystem;

/**
 * 
 * Will display a solarsystem to the player. Extending the Group instead of the Actor to
 * hold own Actor instances Counterpart to the @HexMap. Will display a solarsystem
 * to the player. Extending the Group instead of the Actor to hold own Actor instances
 * 
 * @author ***REMOVED***
 * 
 */
public class SolarMap extends Group implements Disposable {

	/** Solar system information */
	private SolarSystem system;
	private Set<GenericMapObject> childobjects;

	/** Rendering */
	private Stage stage;
	private ShapeRenderer renderer;
	private int offset;

	public SolarMap(SolarSystem system, Set<GenericMapObject> childobjects) {
		this.system = system;
		this.childobjects = childobjects;

		offset = 100;
		renderer = new ShapeRenderer();

	}

	public void setInputToChild() {
		Gdx.input.setInputProcessor(stage);
	}

	public void draw(SpriteBatch batch, float parentAlpha) {

		if (stage == null)
			stage = new Stage();
		stage.clear();

		if (childobjects != null) {
			for (GenericMapObject object : childobjects) {
				stage.addActor(object);
			}
		}

		stage.draw();

		batch.end();
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		renderer.translate(getX() / 2, getY() / 2, 0);
		renderer.begin(ShapeType.Circle);
		renderer.circle((Gdx.graphics.getWidth() / 2) - offset, Gdx.graphics.getHeight() / 2,
				system.getRadius());
		renderer.end();
		batch.begin();

		switch (system.getStar().getClassification()) {
		case BROWNDWARF:
			batch.draw(ResPack.STARS_BROWN_DWARF, Find.getCenter().x
					- (ResPack.SIZE_CELESTIAL_BROWN_DWARF / 2) - offset, Find.getCenter().y
					- (ResPack.SIZE_CELESTIAL_BROWN_DWARF / 2), 0, 0, ResPack.SIZE_CELESTIAL_BROWN_DWARF,
					ResPack.SIZE_CELESTIAL_BROWN_DWARF, 1, 1, 0);
			break;

		case BLUEGIANT:
			batch.draw(ResPack.STARS_BLUE_GIANT, Find.getCenter().x
					- (ResPack.SIZE_CELESTIAL_BLUE_GIANT / 2) - offset, Find.getCenter().y
					- (ResPack.SIZE_CELESTIAL_BLUE_GIANT / 2), 0, 0, ResPack.SIZE_CELESTIAL_BLUE_GIANT,
					ResPack.SIZE_CELESTIAL_BLUE_GIANT, 1, 1, 0);
			break;

		case NEUTRON:
			batch.draw(ResPack.STARS_BLUE_DWARF, Find.getCenter().x
					- (ResPack.SIZE_CELESTIAL_BLUE_DWARF / 2) - offset, Find.getCenter().y
					- (ResPack.SIZE_CELESTIAL_BLUE_DWARF / 2), 0, 0, ResPack.SIZE_CELESTIAL_BLUE_DWARF,
					ResPack.SIZE_CELESTIAL_BLUE_DWARF, 1, 1, 0);
			break;

		case REDDWARF:
			batch.draw(ResPack.STARS_RED_DWARF, Find.getCenter().x
					- (ResPack.SIZE_CELESTIAL_RED_DWARF / 2) - offset, Find.getCenter().y
					- (ResPack.SIZE_CELESTIAL_RED_DWARF / 2), 0, 0, ResPack.SIZE_CELESTIAL_RED_DWARF,
					ResPack.SIZE_CELESTIAL_RED_DWARF, 1, 1, 0);
			break;

		case REDGIANT:
			batch.draw(ResPack.STARS_RED_GIANT, Find.getCenter().x
					- (ResPack.SIZE_CELESTIAL_RED_GIANT / 2) - offset, Find.getCenter().y
					- (ResPack.SIZE_CELESTIAL_RED_GIANT / 2), 0, 0, ResPack.SIZE_CELESTIAL_RED_GIANT,
					ResPack.SIZE_CELESTIAL_RED_GIANT, 1, 1, 0);
			break;

		default:
			break;
		}
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
