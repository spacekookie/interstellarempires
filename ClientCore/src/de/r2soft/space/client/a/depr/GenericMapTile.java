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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import de.r2soft.space.client.core.ScreenHandler;
import de.r2soft.space.client.settings.Resources;
import de.r2soft.space.client.util.ResPack;
import de.r2soft.space.framework.map.SolarSystem;
import de.r2soft.space.framework.objects.Ship;
import de.r2soft.space.framework.players.Alliance.ALLEGIANCE;
import de.r2soft.space.framework.players.Player;
import de.r2soft.space.framework.types.IntVec2;

@Deprecated
public class GenericMapTile extends Actor {

	private Vector2 position;
	private Vector2 size;
	private IntVec2 tileID;
	private ALLEGIANCE ally;
	private SolarSystem childsystem;

	protected IntVec2 id = null;

	/** Player for tile colour */
	private Player claim;

	/**
	 * Main constructor. Creates tile actor with solar system information. The @Solarsystem
	 * object needs it's claimed player. Other values will be given along to the
	 * child system actor.
	 * 
	 * @param x
	 *            coordinate of the requested tile.
	 * @param y
	 *            coordinate of the requested tile.
	 * @param alliance
	 *            of the tile: player, hostile, neutral and friendly.
	 */
	public GenericMapTile(float x, float y, SolarSystem system) {
		System.out.println(system.getClaim());
		position = new Vector2(x, y);
		size = new Vector2(100, 100);
		tileID = id;
		claim = system.getClaim();
		childsystem = system;
		if (claim.equals(Resources._neutralplayer)) {
			ally = ALLEGIANCE.NEUTRAL;
		} else if (claim.getAlliance().equals(
				Resources.thisPlayer.getAlliance())) {
			ally = ALLEGIANCE.FRIENDLY;
		} else {
			ally = claim.equals(Resources.thisPlayer) ? ALLEGIANCE.PLAYER
					: ALLEGIANCE.HOSTILE;
		}
	}

	/**
	 * The constructor will set up the coordinates on which the tile will then
	 * be drawn. Also includes the alliance of the tile.
	 * 
	 * @param x
	 *            coordinate of the requested tile.
	 * @param y
	 *            coordinate of the requested tile.
	 * @param alliance
	 *            of the tile: player, hostile, neutral and friendly.
	 */
	@Deprecated
	public GenericMapTile(float x, float y, ALLEGIANCE a, IntVec2 id) {
		position = new Vector2(x, y);
		size = new Vector2(100, 100);
		tileID = id;
		ally = a;
	}

	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.end();
		batch.begin();

		switch (ally) {
		case FRIENDLY:
			batch.draw(ResPack.TILE_HEX_FRIEND, position.x, position.y, 0, 0,
					size.x, size.y, 1, 1, 0);
			break;

		case HOSTILE:
			batch.draw(ResPack.TILE_HEX_ENEMY, position.x, position.y, 0, 0,
					size.x, size.y, 1, 1, 0);
			break;

		case NEUTRAL:
			batch.draw(ResPack.TILE_HEX_NEUTRAL, position.x, position.y, 0, 0,
					size.x, size.y, 1, 1, 0);
			break;

		case PLAYER:
			batch.draw(ResPack.TILE_HEX_PLAYER, position.x, position.y, 0, 0,
					size.x, size.y, 1, 1, 0);
			break;

		default:
			Gdx.app.log(Resources.LOG_HEX_TILE,
					"fatal error displaying map tile");
			break;
		}

		if (childsystem.getUnits() != null) {
			if (childsystem.hasUnits()) {
				for (Ship unit : childsystem.getUnits()) {
					if (unit.getAllegiance(Resources.thisPlayer).equals(
							ALLEGIANCE.PLAYER)) {
						batch.draw(ResPack.TILE_ADD_FLEET_PLAYER, position.x
								+ (size.x / 4), position.y + (size.y / 4), 0,
								0, (size.x / 4), (size.y / 4), 1, 1, 0);
					}
					if (unit.getAllegiance(Resources.thisPlayer).equals(
							ALLEGIANCE.FRIENDLY)) {
						batch.draw(ResPack.TILE_ADD_FLEET_FRIENDLY, position.x
								+ 2 * (size.x / 4), position.y + 2
								* (size.y / 4), 0, 0, (size.x / 4),
								(size.y / 4), 1, 1, 0);
					}
					if (unit.getAllegiance(Resources.thisPlayer).equals(
							ALLEGIANCE.HOSTILE)) {
						batch.draw(ResPack.TILE_ADD_FLEET_ENEMY, position.x
								+ (size.x / 4), position.y + 2 * (size.y / 4),
								0, 0, (size.x / 4), (size.y / 4), 1, 1, 0);
					}
				}
			}
		} else if (childsystem.hasStructures()) {
			batch.draw(ResPack.TILE_ADD_STATION_PLAYER, position.x + 2
					* (size.x / 4), position.y + (size.y / 4), 0, 0,
					(size.x / 4), (size.y / 4), 1, 1, 0);
		}
	}

	/**
	 * This will register clicks on the corresponding tile actor.
	 * 
	 * @param x
	 *            position of mouse on screen.
	 * 
	 * @param y
	 *            position of mouse on screen.
	 * 
	 * @param touchable
	 *            Whether the actor allows touch events.
	 * 
	 * @return null
	 */
	@Override
	public Actor hit(float x, float y, boolean touchable) {

		if (touchable && getTouchable() == Touchable.enabled) {
			if (Gdx.input.isTouched(0)) {
				if (x > position.x && x < (position.x + size.x)
						&& y > position.y && y < position.y + size.y) {
					Gdx.app.log(Resources.LOG_HEX_TILE, "you clicked: "
							+ tileID);
					ScreenHandler.getInstance().setScreen(
							new StarsystemScreen(ScreenHandler.getInstance(),
									childsystem));
					return this;
				} else {
					// Gdx.app.log(Settings.LOG_HEX_TILE, "nothing there");
				}
			}
		}
		return null;
	}

}
