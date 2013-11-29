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
package de.r2soft.space.client.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import de.r2soft.space.client.settings.Resources;
import de.r2soft.space.client.util.ResPack;
import de.r2soft.space.client.util.Sizes;
import de.r2soft.space.client.util.Translator;
import de.r2soft.space.framework.objects.Fleet;
import de.r2soft.space.framework.objects.Fleet.FleetSize;
import de.r2soft.space.framework.objects.GameObject;
import de.r2soft.space.framework.objects.GameObject.Category;
import de.r2soft.space.framework.objects.Planet;
import de.r2soft.space.framework.objects.Planet.PlanetType;
import de.r2soft.space.framework.objects.Ship;
import de.r2soft.space.framework.objects.Ship.ShipType;
import de.r2soft.space.framework.objects.Structure;
import de.r2soft.space.framework.objects.factory.UnitFactory;
import de.r2soft.space.framework.players.Player;
import de.r2soft.space.framework.types.Allegience.Allegiance;

/**
 * A generic MapObject that will be drawn onto the screen in the Solarmap. May call sub-actors for specific shapes, sizes and habits of
 * objects.
 * 
 * @author Katharina
 * 
 */
public class GenericMapObject extends Actor {

  /** Debug renderer */
  private ShapeRenderer renderer;

  /** Position information on screen */
  private Vector2 position;
  private GameObject orbit;

  /** Is this object selected? */
  private boolean selected;

  /** Global object attributes */
  private Category superclass;
  private Allegiance allegiance;
  private Player claim;
  private String name;

  /** Unit information */

  private ShipType shipType;

  /** Fleet information */
  private FleetSize fleetSize;
  private int fleetCount;

  /** Planet information */
  private PlanetType planetClass;
  private float planetRadius;
  private float planetMass;

  /** Structure information */
  private UnitFactory structureFactory;

  /** For parent classes to work with */
  private Ship unit;
  private Fleet fleet;
  private Planet planet;
  private Structure structue;

  {
	renderer = new ShapeRenderer();
	position = new Vector2();
  }

  /**
   * Constructor only taking a unit.
   * 
   * @param unit
   *          object to be drawn.
   */
  public GenericMapObject(Ship unit) {
	name = unit.getName();
	shipType = unit.getType();
	claim = unit.getClaim();
	allegiance = Translator.friendOrFoe(unit.getClaim(), Resources.thisPlayer);
	System.out.println(unit.getPosition());
	position = unit.getPosition();
	superclass = unit.getSuperclass();
	this.unit = unit;
	orbit = unit.getOrbit();
	System.out.println("Unit in Generic Map Object: " + unit.getName());
  }

  /**
   * Constructor only taking a fleet.
   * 
   * @param fleet
   *          obejct to be drawn.
   */
  public GenericMapObject(Fleet fleet) {
	name = fleet.getName();
	claim = fleet.getClaim();
	fleetSize = fleet.getFleetSize();
	fleetCount = fleet.getCount();
	allegiance = Translator.friendOrFoe(fleet.getClaim(), Resources.thisPlayer);
	position = fleet.getPosition();
	superclass = fleet.getSuperclass();
	this.fleet = fleet;
	orbit = fleet.getOrbit();
  }

  /**
   * Constructor only taking a structure.
   * 
   * @param structure
   */
  public GenericMapObject(Structure structure) {
	this.structue = structure;
	superclass = structure.getSuperclass();
	name = structure.getName();
	claim = structure.getClaim();
	position = structure.getPosition();
	allegiance = Translator.friendOrFoe(structure.getClaim(), Resources.thisPlayer);
	structureFactory = structure.getFactory();
	orbit = structure.getOrbit();
  }

  /**
   * Constructor only taking a planet.
   * 
   * @param planet
   */
  public GenericMapObject(Planet planet) {
	planetClass = planet.getType();
	planetRadius = planet.getRadius();
	planetMass = planet.getMass();
	orbit = planet.getOrbit();
	superclass = planet.getSuperclass();
	this.planet = planet;
  }

  @Deprecated
  public GenericMapObject(float x, float y) {
	this.position.x = x;
	this.position.y = y;
  }

  @Deprecated
  public GenericMapObject(float x, float y, Category type, String name, Player claim, Allegiance allegience) {
	this.position.x = x;
	this.position.y = y;
	this.name = name;
	this.claim = claim;
	this.allegiance = allegience;
  }

  @Override
  public void draw(SpriteBatch batch, float parentAlpha) {
	batch.end();
	batch.begin();

	if (superclass.equals(Category.SHIP)) {
	  switch (this.shipType) {
	  case FIGHTER:
		batch.draw(ResPack.UNITS_FIGHTER_BASIC, position.x, position.y, 0, 0, Sizes.SIZE_FLEET_TINY, Sizes.SIZE_FLEET_TINY, 1, 1, 0);
		break;
	  case CARGO_SMALL:
		batch.draw(ResPack.UNITS_CARGO_SMALL, position.x, position.y, 0, 0, Sizes.SIZE_FLEET_TINY, Sizes.SIZE_FLEET_TINY, 1, 1, 0);
		break;

	  default:
		break;
	  }
	}

	if (selected) {
	  batch.draw(ResPack.GUI_FRAME_SELECTION, position.x - (Sizes.SIZE_GUI_SELECTION_BOX_TINY - Sizes.SIZE_FLEET_TINY), position.y
		  - (Sizes.SIZE_GUI_SELECTION_BOX_TINY - Sizes.SIZE_FLEET_TINY), 0, 0, Sizes.SIZE_GUI_SELECTION_BOX_TINY,
		  Sizes.SIZE_GUI_SELECTION_BOX_TINY, 1, 1, 0);
	}

  }

  /**
   * This will register clicks on the corresponding tile actor.
   * 
   * @param x
   *          position of mouse on screen.
   * 
   * @param y
   *          position of mouse on screen.
   * 
   * @param touchable
   *          Whether the actor allows touch events.
   * 
   * @return null
   */
  @Override
  public Actor hit(float x, float y, boolean touchable) {

	if (touchable && getTouchable() == Touchable.enabled) {
	  if (Gdx.input.isButtonPressed(1)) {
		if (selected) {
		  // TODO: Request move on server
		  position.x = x;
		  position.y = y;
		}
	  }
	  else if (Gdx.input.isTouched(0)) {
		if (x > this.position.x && x < (this.position.x + Sizes.SIZE_GUI_SELECTION_BOX_TINY) && y > this.position.y
			&& y < (this.position.y + Sizes.SIZE_GUI_SELECTION_BOX_TINY)) {
		  selected = true;
		  // setParentSelection("metal");
		}
		else {
		  selected = false;
		  // setParentSelection(null);
		}
	  }
	}
	return null;
  }

  // private void setParentSelection(String s) {
  // if (s != null)
  // SystemScreen.getInstance().setSelectionfocus(this, superclass);
  // else
  // SystemScreen.getInstance().setSelectionfocus(null, null);
  // }

  /** @return selected planet */
  public Planet getPlanetIfExists() {
	return planet;
  }

  /** @return selected unit */
  public Ship getUnitIfExists() {
	return unit;
  }

  /** @return selected structure */
  public Structure getStructureIfExists() {
	return structue;
  }
}
