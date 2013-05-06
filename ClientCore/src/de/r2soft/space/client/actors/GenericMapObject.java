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
import com.badlogic.gdx.utils.Disposable;

import de.r2soft.space.client.settings.Resources;
import de.r2soft.space.client.util.ResPack;
import de.r2soft.space.client.util.Translator;
import de.r2soft.space.framework.objects.GameObject;
import de.r2soft.space.framework.objects.GameObject.TYPE;
import de.r2soft.space.framework.objects.Planet;
import de.r2soft.space.framework.objects.Planet.PLANETCLASS;
import de.r2soft.space.framework.objects.Structure;
import de.r2soft.space.framework.objects.Unit;
import de.r2soft.space.framework.players.Alliance.ALLEGIANCE;
import de.r2soft.space.framework.players.Player;

/**
 * A generic MapObject that will be drawn onto the screen in the Solarmap. May
 * call sub-actors for specific shapes, sizes and habits of objects. A generic
 * MapObject that will be drawn onto the screen in the Solarmap. May call
 * sub-actors for specific shapes, sizes and habits of objects.
 * 
 * @author ***REMOVED***
 * 
 */
@SuppressWarnings("unused")
public class GenericMapObject extends Actor implements Disposable {

	/** The absolute position of the actor */
	private float posX, posY;

	private ALLEGIANCE allegiance;
	private Player claim;
	private GameObject orbit;

	/** Unit information */
	private TYPE type;
	private String flag;
	private Vector2 position;

	/** Unit information */
	private PLANETCLASS planetclass;
	private float planetradius;
	private float planetmass;

	/** Unit movement. May be moved to server */
	@Deprecated
	private Vector2 target, trajectory;

	private ShapeRenderer renderer;
	private boolean selected;
	private boolean moving;

	{
		renderer = new ShapeRenderer();
		target = new Vector2();
		position = new Vector2();
	}

	/**
	 * Constructor only taking the actual unit.
	 * 
	 * @param unit
	 */
	public GenericMapObject(Unit unit) {
		type = unit.getType();
		flag = unit.getFlag();
		claim = unit.getClaim();
		allegiance = Translator.friendOrFoe(unit.getClaim(), Resources.thisPlayer);
		position = unit.getPosition();
	}

	/**
	 * Constructor only taking the actual structure.
	 * 
	 * @param structure
	 */
	public GenericMapObject(Structure structure) {

	}

	/**
	 * Constructor only taking the actual planet.
	 * 
	 * @param planet
	 */
	public GenericMapObject(Planet planet) {
		planetclass = planet.getClassification();
		planetradius = planet.getRadius();
		planetmass = planet.getMass();
		claim = planet.getClaim();
		orbit = planet.getOrbit();
	}

	@Deprecated
	public GenericMapObject getInstance() {
		return this;
	}

	@Deprecated
	public GenericMapObject(float x, float y) {
		this.position.x = x;
		this.position.y = y;
	}

	@Deprecated
	public GenericMapObject(float x, float y, TYPE type, String flag, Player claim,
			ALLEGIANCE allegience) {
		this.position.x = x;
		this.position.y = y;
		this.type = type;
		this.flag = flag;
		this.claim = claim;
		this.allegiance = allegience;
	}

	@Deprecated
	public GenericMapObject(float x, float y, TYPE type2, String flag2, Player claim2) {
	}

	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.end();
		batch.begin();
		if (selected) {
			batch.draw(ResPack.GUI_FRAME_SELECTION, position.x
					- (ResPack.SIZE_GUI_SELECTION_BOX_MEDIUM - ResPack.SIZE_FLEET_MEDIUM), position.y
					- (ResPack.SIZE_GUI_SELECTION_BOX_MEDIUM - ResPack.SIZE_FLEET_MEDIUM), 0, 0,
					ResPack.SIZE_GUI_SELECTION_BOX_MEDIUM, ResPack.SIZE_GUI_SELECTION_BOX_MEDIUM, 1, 1, 0);
		}

		switch (type) {
		case FLEET:
			batch.draw(ResPack.FLEET_FIGHTER_PLAYER, position.x, position.y, 0, 0,
					ResPack.SIZE_FLEET_MEDIUM, ResPack.SIZE_FLEET_MEDIUM, 1, 1, 0);
			break;

		default:
			Gdx.app.log(Resources.LOG_MAP_OBJECT, "fatal error displaying map object");
			break;
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
				System.out.println("Click click");
				if (selected) {
					target.x = Gdx.input.getX();
					target.y = Gdx.input.getY();
					moveLocally();
				}
			}
			else if (Gdx.input.isTouched(0)) {
				if (x > (this.position.x - ResPack.SIZE_GUI_SELECTION_BOX_MEDIUM)
						&& x < (this.position.x + ResPack.SIZE_GUI_SELECTION_BOX_MEDIUM)
						&& y > (this.position.y - ResPack.SIZE_GUI_SELECTION_BOX_MEDIUM)
						&& y < (this.position.y + ResPack.SIZE_GUI_SELECTION_BOX_MEDIUM)) {
					selected = true;
				}
				else {
					selected = false;
				}
			}
		}
		return null;
	}

	private void moveLocally() {
		if (!target.equals(position)) {
			trajectory = new Vector2(target.sub(position));
			trajectory.nor();
			position.add(trajectory.mul(100));
		}
		Vector2 position = new Vector2(this.posX, this.posY);
		trajectory = new Vector2(target.sub(position));
		trajectory.nor();
		position.add(trajectory.x, -trajectory.y);
	}

	@Override
	public void dispose() {
	}

}
