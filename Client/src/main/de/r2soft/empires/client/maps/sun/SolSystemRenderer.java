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

package de.r2soft.empires.client.maps.sun;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

import de.r2soft.empires.client.resources.Assets;
import de.r2soft.empires.client.resources.Values;
import de.r2soft.empires.framework.map.SolarSystem;
import de.r2soft.empires.framework.objects.BaseObject;
import de.r2soft.empires.framework.objects.Fleet;
import de.r2soft.empires.framework.objects.Moon;
import de.r2soft.empires.framework.objects.OrbitalObject;
import de.r2soft.empires.framework.objects.OrbitalStructure;
import de.r2soft.empires.framework.objects.Planet;
import de.r2soft.empires.framework.objects.Ship;
import de.r2soft.empires.framework.objects.Star;
import de.r2soft.empires.framework.types.Allegience;
import de.r2soft.empires.framework.types.Allegience.Allegiance;

/**
 * A custom renderer that renders a solar system with a star in the middle and planets orbiting on circular paths around
 * the star.
 * 
 * @author ***REMOVED***
 * 
 */
public class SolSystemRenderer implements MapRenderer, Disposable {
	private Logger logger = Logger.getLogger(getClass().getSimpleName());
	private boolean yDown = false;
	private float scale;
	private SpriteBatch batch;
	private Camera camera;
	private Rectangle viewBounds;
	private ShapeRenderer renderer;

	/** Display settings from the Container Window */
	private boolean displayLines = true;
	private boolean displayFleets = true;
	private boolean displayPlanets = true;
	private boolean displayMoons = true;
	private boolean displayStructures = true;
	private boolean displayAsteroidBelts = true;

	/* System variables */
	private SolarSystem system;
	private Set<OrbitalObject> orbits;

	public SolSystemRenderer(SolarSystem system, OrthographicCamera camera) {
		this.system = system;
		System.out.println(system.getStar());
		this.camera = camera;
		batch = new SpriteBatch();
		viewBounds = new Rectangle();
		renderer = new ShapeRenderer(10000); // Possibly adjust that value?
		orbits = new HashSet<OrbitalObject>();
	}

	public boolean isYdown() {
		return yDown;
	}

	public void setYDown(boolean yDown) {
		this.yDown = yDown;
	}

	public void setAUScale(float scale) {
		this.scale = scale;
	}

	public float getAUScale() {
		return scale;
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void setView(OrthographicCamera camera) {
		batch.setProjectionMatrix(camera.combined);
		float width = camera.viewportWidth * camera.zoom;
		float height = camera.viewportHeight * camera.zoom;
		viewBounds.set(camera.position.x - width / 2, camera.position.y - height / 2, width, height);
	}

	@Override
	public void setView(Matrix4 projection, float x, float y, float width, float height) {
		batch.setProjectionMatrix(projection);
		viewBounds.set(x, y, width, height);
	}

	@Override
	public void render() {
		renderer.setProjectionMatrix(camera.combined);
		if (displayLines)
			this.renderOrbits();

		// if (displayFleets)
		// this.renderShips();

		batch.begin();
		this.renderStar();

		if (displayPlanets) {
			this.renderPlanets();

			if (displayMoons)
				this.renderMoons();
		}

		// this.renderShips();
		// this.renderStructures();
		batch.end();

	}

	/** Rendering debug orbits for planets and moons. */
	private void renderOrbits() {
		if (!orbits.isEmpty()) {
			renderer.begin(ShapeType.Line);
			for (OrbitalObject o : orbits)
				if (o instanceof Moon) {
					float x = (float) ((Moon) o).getParent().getPosition().getX();
					float y = (float) ((Moon) o).getParent().getPosition().getY();
					renderer.circle(x, y, (float) o.getOrbit().getRadius());
				}
				else
					renderer.circle(0, 0, (float) o.getOrbit().getRadius());
			renderer.end();
		}
	}

	private void renderStructures() {
		for (OrbitalStructure structure : system.getStructures()) {
			batch.draw(getStructureResource(structure), (float) structure.getPosition().getX(), (float) structure
			    .getPosition().getY(), 0, 0, Values.R2_SOLAR_PLAYER_STATION, Values.R2_SOLAR_PLAYER_STATION, 1, 1, 0);
			if (!orbits.contains(structure))
				orbits.add(structure);
		}
	}

	/** Renders the star with appropriate textures and size */
	private void renderStar() {
		batch.draw(getStarResource(system.getStar()), -(getStarSize(system.getStar()) / 2),
		    -(getStarSize(system.getStar()) / 2), 0, 0, getStarSize(system.getStar()), getStarSize(system.getStar()),
		    1, 1, 0);
	}

	/** Render the planets of the solar system */
	private void renderPlanets() {
		for (Planet planet : system.getPlanets()) {
			float x = (float) planet.getPosition().getX() - (getPlanetSize(planet) / 2);
			float y = (float) planet.getPosition().getY() - (getPlanetSize(planet) / 2);

			batch.draw(getPlanetResource(planet), x, y, 0, 0, getPlanetSize(planet), getPlanetSize(planet), 1, 1, 0);

			if (!orbits.contains(planet))
				orbits.add(planet);
		}
	}

	/** Renders the moons of a solar system, independent of their planets. */
	public void renderMoons() {
		for (Moon moon : system.getMoons()) {
			float x = (float) moon.getPosition().getX() - (Values.R2_SOLAR_CELESTIAL_MOON_ROCKY / 2);
			float y = (float) moon.getPosition().getY() - (Values.R2_SOLAR_CELESTIAL_MOON_ROCKY / 2);

			batch.draw(getMoonResource(moon), x, y, 0, 0, Values.R2_SOLAR_CELESTIAL_MOON_ROCKY,
			    Values.R2_SOLAR_CELESTIAL_MOON_ROCKY, 1, 1, 0);

			if (!orbits.contains(moon))
				orbits.add(moon);
		}
	}

	/**
	 * Checks if a fleet has a single unit, then renders either the ship or the fleet icon with the appropriate icon
	 */
	private void renderShips() {
		for (Fleet fleet : system.getUnits())
			if (fleet.getUnits() instanceof Ship)
				batch.draw(getShipResource((Ship) fleet.getUnits()), (float) fleet.getPosition().getX(), (float) fleet
				    .getPosition().getY(), Values.R2_SOLAR_PLAYER_SHIP / 2, Values.R2_SOLAR_PLAYER_SHIP / 2,
				    Values.R2_SOLAR_PLAYER_SHIP, Values.R2_SOLAR_PLAYER_SHIP, 1, 1, 0);

			else if (fleet.getUnits() instanceof Set<?>)
				batch.draw(getFleetResource(fleet), (float) fleet.getPosition().getX(),
				    (float) fleet.getPosition().getY(), Values.R2_SOLAR_PLAYER_FLEET / 2,
				    Values.R2_SOLAR_PLAYER_FLEET / 2, Values.R2_SOLAR_PLAYER_FLEET, Values.R2_SOLAR_PLAYER_FLEET, 1, 1, 0);

	}

	/**
	 * This method returns whatever object the player has clicked on. See TODO file inside.
	 * 
	 * @param x
	 *          coordinate in the world
	 * @param y
	 *          coordinate in the world
	 * @return Returns a set of Objects that MIGHT have been clicked.
	 */
	public Set<BaseObject> getObjectsAtCoordinates(double x, double y) {

		// TODO: Get items from value manager with range of the click.
		return null;
	}

	@Override
	public void render(int[] layers) {

	}

	/**
	 * ###############################################################
	 * 
	 * Logic to pick Resources to render
	 * 
	 * ###############################################################
	 */

	/** Gets Star TextureRegion depending on Star Type */
	private TextureRegion getStarResource(Star star) {

		switch (star.getType()) {
		case STAR_RED_GIANT:
			return Assets.R2_SOLAR_STAR_REDGIANT;
		case STAR_RED_DWARF:
			return Assets.R2_SOLAR_STAR_REDDWARF;
		case STAR_BLUE_DWARF:
			return Assets.R2_SOLAR_STAR_BLUEDWARF;
		case STAR_BLUE_GIANT:
			return Assets.R2_SOLAR_STAR_BLUEGIANT;
		case STAR_BROWN_DWARF:
			return Assets.R2_SOLAR_STAR_BROWNDWARF;
		case STAR_BLACK_HOLE:
			return Assets.R2_SOLAR_STAR_BLACKHOLE;
		default:
			return null;
		}
	}

	/** Gets the size for the star texture. If star-type is null it returns default value of 150f */
	private float getStarSize(Star star) {
		switch (star.getType()) {
		case STAR_RED_GIANT:
			return Values.R2_SOLAR_CELESTIAL_STAR_REDGIANT;
		case STAR_RED_DWARF:
			return Values.R2_SOLAR_CELESTIAL_STAR_REDDWARF;
		case STAR_BLUE_DWARF:
			return Values.R2_SOLAR_CELESTIAL_STAR_BLUEDWARF;
		case STAR_BLUE_GIANT:
			return Values.R2_SOLAR_CELESTIAL_STAR_BLUEGIANT;
		case STAR_BROWN_DWARF:
			return Values.R2_SOLAR_CELESTIAL_STAR_BROWNDWARF;
		case STAR_BLACK_HOLE:
			return Values.R2_SOLAR_CELESTIAL_STAR_BLACKHOLE;
		default:
			return 150f;
		}
	}

	private TextureRegion getFleetResource(Fleet fleet) {
		Allegiance temp = Allegience.validate(fleet.getClaim(), Values.PLAYER);

		if (temp.equals(Allegiance.SELF))
			return Assets.R2_SOLAR_FLEET_SELF;
		else if (temp.equals(Allegiance.HOSTILE))
			return Assets.R2_SOLAR_FLEET_HOSTILE;
		else if (temp.equals(Allegiance.NEUTRAL))
			return Assets.R2_SOLAR_FLEET_NEUTRAL;
		else
			logger.error("ALLEGIANCE COULDN'T BE VALIDATED!");
		return null;
	}

	private TextureRegion getShipResource(Ship ship) {
		Allegiance temp = Allegience.validate(ship.getClaim(), Values.PLAYER);

		if (temp.equals(Allegiance.SELF))
			return Assets.R2_SOLAR_SHIP_SELF;
		else if (temp.equals(Allegiance.HOSTILE))
			return Assets.R2_SOLAR_SHIP_HOSTILE;
		else if (temp.equals(Allegiance.NEUTRAL))
			return Assets.R2_SOLAR_SHIP_NEUTRAL;
		else
			logger.error("ALLEGIANCE COULDN'T BE VALIDATED!");
		return null;
	}

	private TextureRegion getStructureResource(OrbitalStructure structure) {
		Allegiance temp = Allegience.validate(structure.getClaim(), Values.PLAYER);

		if (temp.equals(Allegiance.SELF))
			return Assets.R2_SOLAR_STATION_SELF;
		else if (temp.equals(Allegiance.HOSTILE))
			return Assets.R2_SOLAR_STATION_HOSTILE;
		else if (temp.equals(Allegiance.NEUTRAL))
			return Assets.R2_SOLAR_STATION_NEUTRAL;
		else
			logger.error("ALLEGIANCE COULDN'T BE VALIDATED!");
		return null;
	}

	private TextureRegion getPlanetResource(Planet planet) {
		switch (planet.getType()) {
		case PLANET_EARTHY:
			return Assets.R2_SOLAR_PLANET_EARTHY;
		case PLANET_FLAMY:
			return Assets.R2_SOLAR_PLANET_FLAMY;
		case PLANET_ICY:
			return Assets.R2_SOLAR_PLANET_ICY;
		case PLANET_GASSY:
			return Assets.R2_SOLAR_PLANET_GASSY;
		default:
			return null;
		}
	}

	private float getPlanetSize(Planet planet) {
		switch (planet.getType()) {
		case PLANET_EARTHY:
			return Values.R2_SOLAR_CELESTIAL_PLANET_EARTHY;
		case PLANET_FLAMY:
			return Values.R2_SOLAR_CELESTIAL_PLANET_FLAMY;
		case PLANET_ICY:
			return Values.R2_SOLAR_CELESTIAL_PLANET_ICY;
		case PLANET_GASSY:
			return Values.R2_SOLAR_CELESTIAL_PLANET_GASSY;
		default:
			return 150f;
		}
	}

	private TextureRegion getMoonResource(Moon moon) {
		switch (moon.getType()) {
		case MOON_ROCKY:
			return Assets.R2_SOLAR_MOON_ROCKY;
		default:
			return null;
		}
	}

}
